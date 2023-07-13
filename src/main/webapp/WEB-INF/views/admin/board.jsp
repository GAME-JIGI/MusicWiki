<%@ page import="com.gamejigi.wiki.util.PaginationResponse" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Tables</title>

    <!-- Custom fonts for this template -->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <script src="/util/util.js"></script>

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <%@ include file="includes/sidebar.jsp" %>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <%@ include file="includes/header.jsp" %>

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800">게시판 관리</h1>

                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <a tabindex="0" onclick="fetchRedirect('/admin/board/test', 'post', '/admin/board')">
                            <button type="button" class="btn btn-primary">
                                테스트 데이터 생성하기
                            </button>
                        </a>
                        <a tabindex="0" onclick="fetchRedirect('/admin/board/test', 'delete', '/admin/board')">
                            <button type="button" class="btn btn-primary">
                                전체삭제하기
                            </button>
                        </a>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <div class="dataTables_wrapper dt-bootstrap4">
                                <div class="row">
                                    <div class="col-sm-12 col-md-6">
                                        <div class="dataTables_length"><label>줄 수 <select name="length"
                                                class="custom-select custom-select-sm form-control form-control-sm">
                                            <option value="10">10</option>
                                            <option value="25">25</option>
                                            <option value="50">50</option>
                                            <option value="100">100</option>
                                        </select></label></div>
                                    </div>
                                    <div class="col-sm-12 col-md-6">
                                        <div class="dataTables_filter">
                                            <label>검색 :
                                                <input type="search" class="form-control form-control-sm" placeholder="">
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <table class="table table-bordered dataTable" width="100%"
                                               cellspacing="0" role="grid" aria-describedby="dataTable_info"
                                               style="width: 100%;">
                                            <thead>
                                            <tr role="row">
                                                <th class="sorting sorting_asc" tabindex="0">번호
                                                </th>
                                                <th class="sorting" tabindex="0">게시판 이름
                                                </th>
                                                <th class="sorting" tabindex="0">카테고리
                                                </th>
                                                <th class="sorting" tabindex="0">작업자 이름
                                                </th>
                                                <th class="sorting" tabindex="0">생성 일시
                                                </th>
                                                <th class="sorting" tabindex="0">수정 일시
                                                </th>
                                            </tr>
                                            </thead>
                                            <tfoot>
                                            <tr>
                                                <th rowspan="1" colspan="1">번호</th>
                                                <th rowspan="1" colspan="1">게시판 이름</th>
                                                <th rowspan="1" colspan="1">카테고리</th>
                                                <th rowspan="1" colspan="1">작업자 이름</th>
                                                <th rowspan="1" colspan="1">생성 일시</th>
                                                <th rowspan="1" colspan="1">수정 일시</th>
                                            </tr>
                                            </tfoot>
                                            <tbody>

                                            <c:forEach var="row" items="${pagination.columnList}">
                                            <tr class="odd">
                                                <td class="sorting_1">
                                                        ${row.id}
                                                </td>
                                                <td>
                                                        ${row.name}
                                                </td>
                                                <td>
                                                        ${row.category.name}
                                                </td>
                                                <td>
                                                        ${row.su.name}
                                                </td>
                                                <td>
                                                        ${row.createdDate}
                                                </td>
                                                <td>
                                                        ${row.modifiedDate}
                                                </td>
                                            </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12 col-md-5">
                                        <div class="dataTables_info">Showing 1 to 1 of 1 entries
                                        </div>
                                    </div>
                                    <div class="col-sm-12 col-md-7">
                                        <div class="dataTables_paginate paging_simple_numbers">
                                            <ul class="pagination" style="justify-content: center">
                                                <c:if test="${pagination.prevButton}">
                                                    <li class="paginate_button page-item previous">
                                                        <a onclick="movePage('/admin/board', ${pagination.prevPage}, '');" tabindex="0" class="page-link">
                                                            이전
                                                        </a>
                                                    </li>
                                                </c:if>
                                                <c:forEach var="page" items="${pagination.pageList}">
                                                    <c:set var="active" value="${pagination.request.page == page ? 'active' : ''}" />
                                                    <li class="paginate_button page-item ${active}">
                                                        <a onclick="movePage('/admin/board', ${page}, '');" tabindex="0" class="page-link">
                                                            ${page + 1}
                                                        </a>
                                                    </li>
                                                </c:forEach>
                                                <c:if test="${pagination.nextButton}">
                                                    <li class="paginate_button page-item next">
                                                        <a onclick="movePage('/admin/board', ${pagination.nextPage}, '');" tabindex="0" class="page-link">
                                                            다음
                                                        </a>
                                                    </li>
                                                </c:if>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Your Website 2020</span>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="login.html">Logout</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

</body>

</html>