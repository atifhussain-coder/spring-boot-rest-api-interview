<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Library Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .navbar-nav .nav-link.active {
            font-weight: bold;
            color: #007bff !important;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="/web/members">Members</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/web/books">Books</a>
            </li>
        </ul>
        <ul class="navbar-nav ms-auto">
            <li class="nav-item">
                <a class="nav-link" href="/logout">Logout</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container mt-4">
    <h1>Member Form</h1>
    <h2 th:if="${member.id == null}">Add New Member</h2>
    <h2 th:if="${member.id != null}">Edit Member</h2>

    <form th:action="@{/web/members}" th:object="${member}" method="post">
        <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"/>
        <div class="form-group" style="padding-bottom: 20px ">
            <label for="name">Name</label>
            <input type="text" id="name" class="form-control" th:field="*{name}" required>
        </div>
        <div class="form-group" style="padding-bottom: 20px ">
            <label for="email">Email</label>
            <input type="email" id="email" class="form-control" th:field="*{email}" required>
        </div>
        <input type="hidden" th:field="*{id}" />
        <div class="form-group" style="margin-top: 20px ">
        <button type="submit" class="btn btn-primary">Save</button>
        <a href="/web/members" class="btn btn-secondary">Cancel</a>
        </div>
    </form>
</div>
</body>
</html>
