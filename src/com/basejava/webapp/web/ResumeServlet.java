package com.basejava.webapp.web;

import com.basejava.webapp.Config;
import com.basejava.webapp.model.*;
import com.basejava.webapp.storage.Storage;
import com.basejava.webapp.util.DateUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/Users/metamorfag/IdeaProjects/basejava/web/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
            case "view":
            case "edit":
                r = storage.get(uuid);
                break;
            default:
                throw new IllegalArgumentException("Action" + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(("view".equals(action) ? "/Users/metamorfag/IdeaProjects/basejava/web/WEB-INF/jsp/view.jsp" : "/Users/metamorfag/IdeaProjects/basejava/web/WEB-INF/jsp/edit.jsp"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8 ");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume r = storage.get(uuid);
        r.setFullName(fullName);
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                r.addContact(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }

        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] values = request.getParameterValues(type.name());
            if (values.length < 2) {
                r.getSections().remove(type);
            } else {
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        r.setSections(type, new TextSection(value));
                        break;
                    case ACHIEVEMENTS:
                    case QUALIFICATIONS:
                        r.setSection(type, new ListSection(value.split("\\n")));
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        List<Organization> organizations = new ArrayList<>();
                        String[] urls = request.getParameterValues(type.name() + "url");
                        for (int i = 0; i < values.length; i++) {
                            String name = values[i];
                            if (name != null) {
                                List<Organization.Position> positions = new ArrayList<>();
                                String pos = type.name() + i;
                                String[] startDates = request.getParameterValues(pos + "startDate");
                                String[] endDates = request.getParameterValues(pos + "endDate");
                                String[] titles = request.getParameterValues(pos + "title");
                                String[] descriptions = request.getParameterValues(pos + "description");
                                for (int j = 0; j < titles.length; j++) {
                                    positions.add(new Organization.Position(DateUtil.parse(startDates[j]), DateUtil.parse(endDates[j]), titles[j], descriptions[j]));
                                }
                                organizations.add(new Organization(positions, new Link(name, urls[i])));
                            }
                        }
                        r.setSection(type, new OrganizationSection(organizations));
                        break;
                }
            }
        }
        storage.update(r);
        response.sendRedirect("resume");
    }
}
