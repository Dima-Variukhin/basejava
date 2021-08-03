package com.basejava.webapp.web;

import com.basejava.webapp.Config;
import com.basejava.webapp.model.ContactType;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class ResumeServlet extends HttpServlet {
    Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Writer writer = response.getWriter();
        response.getWriter().write(
                """
                        <!DOCTYPE html>
                        <html>
                        <head>
                        <style>
                        table {
                          font-family: arial, sans-serif;
                          border-collapse: collapse;
                          width: 100%;
                        }

                        td, th {
                          border: 1px solid #dddddd;
                          text-align: left;
                          padding: 8px;
                        }

                        tr:nth-child(even) {
                          background-color: #dddddd;
                        }
                        </style>
                        </head>
                        <body>

                        <h2>HTML Table</h2>

                        <table>
                          <tr>
                            <th>Contact</th>
                            <th>Country</th>
                          </tr>
                          <tr>
                          </tr>
                          <tr>
                          </tr>
                          <tr>
                          </tr>
                          <tr>
                          </tr>
                          <tr>
                          </tr>
                          <tr>
                          </tr>
                        </table>

                        </body>
                        </html>""");

        for (Resume resume : storage.getAllSorted()) {
            writer.write(
                    "<tr>\n" +
                            "     <td><a href=\"resume?uuid=" + resume.getUuid() + "\">" + resume.getFullName() + "</a></td>\n" +
                            "     <td>" + resume.getContact(ContactType.PHONE) + "</td>\n" +
                            "</tr>\n");
        }
        writer.write("</table>\n" +
                "</section>\n" +
                "</body>\n" +
                "</html>\n");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
