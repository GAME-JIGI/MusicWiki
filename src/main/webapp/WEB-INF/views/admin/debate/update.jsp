<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="documents" scope="request" type="java.util.List<com.gamejigi.wiki.domain.document.Document>"/>
<jsp:useBean id="debate" scope="request" type="com.gamejigi.wiki.domain.debate.Debate"/>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>토론 관리 - 수정</title>

    <!-- Custom fonts for this template-->
    <link href="/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/admin/css/sb-admin-2.min.css" rel="stylesheet">

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
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">토론 관리 - 수정</h1>
                </div>

                <form id="debate-form">
                    <input type="hidden" name="id" value="${debate.id}" />

                    <div class="row">

                        <!-- 토론 이름 -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                토론 이름</div>
                                            <input name="name" type="text" class="form-control" value="${debate.name}" aria-label="">
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-tag fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- 문서 이름 -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                문서 이름</div>
                                            <select name="document" class="form-select" aria-label="">
                                                <c:forEach var="document" items="${documents}">
                                                    <c:set var="selected" value="${document.id == debate.document.id ? 'selected' : ''}" />
                                                    <option value="${document.id}" ${selected}>
                                                            ${document.name}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-tag fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- 댓글 상태 -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                댓글 상태</div>
                                            <select id="commentAble" name="commentAble" class="form-select" aria-label="">
                                                <option value=0>열린 토론</option>
                                                <option value=1>닫힌 토론</option>
                                                <option value=2>멈춘 토론</option>
                                            </select>
                                        </div>
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                잠금 시간</div>
                                            <input id="lockTime" name="lockTime" type="datetime-local" class="form-control" aria-label="" readonly>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-tag fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                </form>

                <hr class="border border-primary border-3 opacity-75">

                <div class="row">

                    <!-- 수정 -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            수정</div>
                                        <a tabindex="0" onclick="patchRedirect('/admin/debate', 'debate-form', '/admin/debate')">
                                            <button type="button" class="btn btn-primary">
                                                <div class="h5 mb-0 font-weight-bold">수정</div>
                                            </button>
                                        </a>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-save fa-2x text-gray-300"></i>
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

<script>
    // 댓글 상태 변경 시 이벤트 처리
    document.getElementById('commentAble').addEventListener('change', function() {
        var commentAbleValue = this.value;
        var lockTimeInput = document.getElementById('lockTime');

        // 댓글 상태가 2 (멈춘 토론)인 경우에만 잠금 시간 입력 필드를 읽기 전용으로 설정
        if (commentAbleValue == 2) {
            lockTimeInput.readOnly = false; // 읽기 전용 해제
            lockTimeInput.value = ''; // 잠금 시간 입력값 초기화
        } else {
            lockTimeInput.readOnly = true; // 읽기 전용으로 설정
            lockTimeInput.value = ''; // 잠금 시간 입력값 초기화
        }
    });
</script>

</body>

</html>