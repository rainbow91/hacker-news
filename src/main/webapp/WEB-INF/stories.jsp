<%--
    Licensed to the Apache Software Foundation (ASF) under one or more
    contributor license agreements.  See the NOTICE file distributed with
    this work for additional information regarding copyright ownership.
    The ASF licenses this file to You under the Apache License, Version 2.0
    (the "License"); you may not use this file except in compliance with
    the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="language" value="${pageContext.request.locale}"/>
<fmt:setLocale value="${language}"/>

<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <title>Moviefun</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="../assets/css/bootstrap.css" rel="stylesheet">
    <link href="../assets/css/movie.css" rel="stylesheet">
    <style>
        body {
            padding-top: 60px;
            /* 60px to make the container go all the way to the bottom of the topbar */
        }
    </style>
    <link href="../assets/css/bootstrap-responsive.css" rel="stylesheet">

</head>

<body>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#">Moviefun</a>
        </div>
    </div>
</div>

<div class="container">

    <c:if test="${requestScope.story}">
        <c:set var = "story" scope = "session" value = "${requestScope.story}"/>
        <div>
            <h1>Edit Story</h1>
            <table width="500">
                <tr>
                    <td><b>${story.rating}</b></td>
                    <td><b><input type="text" name="updatedTitle" value="${story.title}"/></b></td>
                </tr>
                <tr>
                    <td span="2">
                        <button type="button" onclick="window.location.href='/stories/${story.id}/${updatedTitle}'">Save</button>
                        <button type="button" onclick="window.location.href='/stories'">Cancel</button>
                    </td>
                </tr>
            </table>
            Rating: 2 &nbsp;&nbsp:
            Title:
            <input type="text" name="updatedTitle">
            <button type="button" onclick="<a href='http://stories/${story.id}/${updatedTitle}'/>;
            alert('Hello World!')">Save</button>
            <button type="button" onclick="alert('Hello World!')">Cancel</button>
        </div>
    </c:if>

    <h1>Hacker News Top 10 Stories</h1>

    <h2>Albums in the database</h2>
    <table width="500">
        <tr>
            <td><b>Rating</b></td>
            <td><b>Title</b></td>
            <td><b>Action</b></td>
        </tr>

        <c:forEach items="${requestScope.stories}" var="story">
            <tr>
                <td> ${story.rating} </td>
                <td><a href="${story.url}"> ${story.title}</a> </td>
                <td><button type="button"
 onclick="${currentSory}=story; window.location.href='/stories'"/>Edit</button>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
<!-- /container -->
</body>
</html>
