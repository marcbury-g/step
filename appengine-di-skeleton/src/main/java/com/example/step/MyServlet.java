// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.example.step;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.inject.Inject;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/servlet")
public class MyServlet extends HttpServlet {
    private Database database;  // We need a Database to do some work

    // The database comes from somewhere else. Where? That's not my job, that's
    // the job of whoever constructs me: they can choose which database to use.
    @Inject
    MyServlet(Database database) {
        this.database = database;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String value = request.getParameter("value");
        if(value == null) {
            response.getWriter().println(database.getValue());
        } else {
            response.getWriter().println(value);
        }
    }
}
