<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="pagination" scope="request" type="com.gamejigi.wiki.util.PaginationResponse<com.gamejigi.wiki.domain.debateComment.DebateComment>"/>
<jsp:useBean id="parameter" scope="request" type="java.lang.String"/>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>토론 댓글 관리</title>

    <!-- Custom fonts for this template -->
    <link href="/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/admin/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="/admin/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <script src="/util/util.js"></script>

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <%@ include file="../includes/sidebar.jsp" %>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <%@ include file="../includes/header.jsp" %>

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800">토론 댓글 관리</h1>

                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <a tabindex="0" onclick="fetchRedirect('/admin/debateComment/test', 'post', '/admin/debateComment')">
                            <button type="button" class="btn btn-primary">
                                테스트 데이터 생성하기
                            </button>
                        </a>
                        <a tabindex="0" onclick="fetchRedirect('/admin/debateComment/test', 'delete', '/admin/debateComment')">
                            <button type="button" class="btn btn-primary">
                                전체삭제하기
                            </button>
                        </a>
                        <a tabindex="0" href="/admin/debateComment/create">
                            <button type="button" class="btn btn-primary">
                                생성
                            </button>
                        </a>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <div class="dataTables_wrapper dt-bootstrap4">
                                <div class="row">
                                    <div class="col-sm-12 col-md-4">
                                        <c:set var="size" value="${pagination.request.maxSize}" />
                                        <div class="dataTables_length">
                                            <label>
                                                줄 수
                                                <select name="length" id="debateComment_size" class="custom-select custom-select-sm form-control form-control-sm"
                                                        onchange="moveParam('/admin/debateComment', editParam('${parameter}', 'size', getValue('debateComment_size')));">
                                                    <option value="10" ${size == 10 ? "selected" : ""}>10</option>
                                                    <option value="25" ${size == 25 ? "selected" : ""}>25</option>
                                                    <option value="50" ${size == 50 ? "selected" : ""}>50</option>
                                                    <option value="100" ${size == 100 ? "selected" : ""}>100</option>
                                                </select>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 col-md-4">
                                        <c:set var="type" value="${pagination.request.searchType}" />
                                        <div class="dataTables_length">
                                            <label>
                                                검색 종류
                                                <select name="type" id="debateComment_searchType" class="custom-select custom-select-sm form-control form-control-sm"
                                                        onchange="moveParam('/admin/debateComment', editParam('${parameter}', 'type', getValue('debateComment_searchType')));">
                                                    <option value="name" ${type == "name" ? "selected" : ""}>댓글 내용</option>
                                                    <option value="document" ${type == "document" ? "selected" : ""}>문서 이름</option>
                                                    <option value="debate" ${type == "debate" ? "selected" : ""}>토론 이름</option>
                                                    <option value="writer" ${type == "writer" ? "selected" : ""}>작성자 이름</option>
                                                </select>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 col-md-4">
                                        <div class="dataTables_filter">
                                            <label>
                                                검색 :
                                                <input type="search" id="debateComment_search" class="form-control form-control-sm" placeholder=""
                                                       value="${param['search']}"
                                                       onchange="moveParam('/admin/debateComment', editParam('${parameter}', 'search', getValue('debateComment_search')))">
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <c:set var="sort_col" value="${pagination.request.sortColumn}" />
                                <c:set var="sort_order" value="${pagination.request.sort}"/>

                                <c:set var="arrow_images_str" value="sorting,sorting_asc,sorting_desc"/>
                                <c:set var="arrow_images" value="${fn:split(arrow_images_str, ',')}"/>

                                <c:set var="cols_str" value="id,name,document.name,debate.name,su.name,createdDate,modifiedDate"/>
                                <c:set var="cols" value="${fn:split(cols_str, ',')}"/>

                                <c:set var="contents_str" value="번호,토론 댓글 내용,문서,토론,작업자 이름,생성 일시,수정 일시"/>
                                <c:set var="contents" value="${fn:split(contents_str, ',')}"/>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <table class="table table-bordered dataTable" width="100%"
                                               cellspacing="0" role="grid" aria-describedby="dataTable_info"
                                               style="width: 100%;">
                                            <thead>
                                            <tr role="row">
                                                <c:forEach var="col" items="${cols}" varStatus="status">
                                                    <c:set var="is_sort_col" value="${sort_col == col}"/>
                                                    <c:set var="content" value="${contents[status.index]}"/>

                                                    <c:set var="next_order" value="${is_sort_col ? (sort_order == 2 ? 1 : 2) : 1}"/>
                                                    <c:set var="arrow_image" value="${is_sort_col ? arrow_images[sort_order] : ''}"/>
                                                    <th id="debateComment_column_${col}" class="sorting ${arrow_image}" tabindex="0"
                                                        onclick="moveParam('/admin/debateComment', editParamSort('${parameter}', '${next_order}', '${col}'));">
                                                            ${content}
                                                    </th>
                                                </c:forEach>
                                                <th>상세 보기
                                                </th>
                                            </tr>
                                            </thead>
                                            <tfoot>
                                            <tr>
                                                <th rowspan="1" colspan="1">번호</th>
                                                <th rowspan="1" colspan="1">토론 댓글 내용</th>
                                                <th rowspan="1" colspan="1">문서</th>
                                                <th rowspan="1" colspan="1">토론</th>
                                                <th rowspan="1" colspan="1">작업자 이름</th>
                                                <th rowspan="1" colspan="1">생성 일시</th>
                                                <th rowspan="1" colspan="1">수정 일시</th>
                                                <th rowspan="1" colspan="1">상세 보기</th>
                                            </tr>
                                            </tfoot>
                                            <tbody>

                                            <c:forEach var="row" items="${pagination.columnList}">
                                                <tr class="odd">
                                                    <td class="sorting_1">
                                                            ${row.id}
                                                    </td>
                                                    <td>
                                                            ${row.content}
                                                    </td>
                                                    <td>
                                                            ${row.document.name}
                                                    </td>
                                                    <td>
                                                            ${row.debate.name}
                                                    </td>
                                                    <td>
                                                            ${row.writer.name}
                                                    </td>
                                                    <td>
                                                            ${row.createdDate}
                                                    </td>
                                                    <td>
                                                            ${row.modifiedDate}
                                                    </td>
                                                    <td>
                                                        <a tabindex="0" onclick="moveParam('/admin/debateComment/detail', editParam('${parameter}', 'id', ${row.id}));">
                                                            <button type="button" class="btn btn-primary">
                                                                상세 보기
                                                            </button>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12 col-md-5">
                                        <div class="dataTables_info">
                                            ${pagination.request.page * pagination.request.maxSize + 1}
                                            ~ ${(pagination.request.page + 1) * pagination.request.maxSize + 1}
                                            / ${pagination.rowsCount}
                                        </div>
                                    </div>
                                    <div class="col-sm-12 col-md-7">
                                        <div class="dataTables_paginate paging_simple_numbers">
                                            <ul class="pagination" style="justify-content: center">
                                                <c:if test="${pagination.prevButton}">
                                                    <li class="paginate_button page-item previous">
                                                        <a onclick="moveParam('/admin/debateComment', editParam('${parameter}', 'page', ${pagination.prevPage}));"
                                                           tabindex="0" class="page-link">
                                                            이전
                                                        </a>
                                                    </li>
                                                </c:if>
                                                <c:forEach var="page" items="${pagination.pageList}">
                                                    <c:set var="active" value="${pagination.request.page == page ? 'active' : ''}" />
                                                    <li class="paginate_button page-item ${active}">
                                                        <a onclick="moveParam('/admin/debateComment', editParam('${parameter}', 'page', ${page}));"
                                                           tabindex="0" class="page-link">
                                                                ${page + 1}
                                                        </a>
                                                    </li>
                                                </c:forEach>
                                                <c:if test="${pagination.nextButton}">
                                                    <li class="paginate_button page-item next">
                                                        <a onclick="moveParam('/admin/debateComment', editParam('${parameter}', 'page', ${pagination.nextPage}));"
                                                           tabindex="0" class="page-link">
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
<script src="/admin/vendor/jquery/jquery.min.js"></script>
<script src="/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="/admin/js/sb-admin-2.min.js"></script>

</body>

</html>