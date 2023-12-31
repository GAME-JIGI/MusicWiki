<%@ page import="java.time.LocalDate" %>
<%@ page import="org.springframework.web.bind.annotation.RequestParam" %>
<%@ page import="org.springframework.web.bind.annotation.PostMapping" %>
<%@ page import="org.springframework.web.bind.annotation.ResponseBody" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<jsp:useBean id="member" scope="request" type="java.util.List<com.gamejigi.wiki.domain.member.Member>"/>--%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>회원 관리 - 생성</title>

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
                        <h1 class="h3 mb-0 text-gray-800">회원 관리 - 생성</h1>
                    </div>

                    <form id="member-form">

                        <div class="row">

                            <!-- 회원 아이디 -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-success shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                    회원 아이디</div>
                                                <input name="user_id" type="text" class="form-control" aria-label="">
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-tag fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- 회원 비밀번호 -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-success shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                    회원 비밀번호</div>
                                                <input name="pw" type="text" class="form-control" aria-label="">
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-tag fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- 회원 전화번호 -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-success shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                    회원 전화번호</div>
<%--                                                <input name="phone" type="text" class="form-control" aria-label="">--%>
                                                <input name="phone1" type="text" size="3" maxlength="3" class="" aria-label=""> -
                                                <input name="phone2" type="text" size="4" maxlength="4" class="" aria-label=""> -
                                                <input name="phone3" type="text" size="4" maxlength="4" class="" aria-label="">
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-tag fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- 회원 이메일 -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-success shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                    회원 이메일</div>
                                                <input name="email" type="text" class="form-control" aria-label="">
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-tag fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- 회원 비밀번호 -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-success shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                    회원 이름</div>
                                                <input name="name" type="text" class="form-control" aria-label="">
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-tag fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- 회원 생일 -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-success shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                    회원 생일</div>
<%--                                                <input name="birth" type="text" class="form-control" aria-label="">--%>
                                                <input name="birth1" type="text" size="4" maxlength="4" class="" aria-label=""> -
                                                <input name="birth2" type="text" size="2" maxlength="2" class="" aria-label=""> -
                                                <input name="birth3" type="text" size="2" maxlength="2" class="" aria-label="">
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-tag fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- 회원 성별 -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-success shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                    회원 성별</div>
                                                <input name="gender" type="radio" value="true" class="" aria-label="" checked> 남성 <br>
                                                <input name="gender" type="radio" value="false" class="" aria-label=""> 여성
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-tag fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!--관리자 유무-->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-success shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                    관리자 유무</div>
                                                <input name="role" type="radio" value="USER" class="" aria-label="" checked> 회원 <br>
                                                <input name="role" type="radio" value="ADMIN" class="" aria-label=""> 관리자
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

                        <!-- 생성 -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-primary shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                생성</div>
                                            <a tabindex="0" onclick="postRedirect('/admin/member', 'member-form', '/admin/member')">
                                                <button type="button" class="btn btn-primary">
                                                    <div class="h5 mb-0 font-weight-bold">생성</div>
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

</body>

</html>