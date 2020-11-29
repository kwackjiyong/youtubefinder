<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>채널검색은 YouTubeFinder</title>
<!-- plugins:css -->
<link rel="stylesheet"
	href="<c:url value='/resources/template/vendors/mdi/css/materialdesignicons.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/template/vendors/base/vendor.bundle.base.css'/>">
<!-- endinject -->
 <link rel="stylesheet" href="<c:url value='/resources/template/vendors/datatables.net-bs4/dataTables.bootstrap4.css'/>">
<!-- inject:css -->
<link rel="stylesheet"
	href="<c:url value='/resources/template/css/style.css'/>">
<!-- endinject -->
<link rel="shortcut icon"
	href="<c:url value='/resources/template/images/YF_logo_small.png'/>" />
<!-- DatetimePicker -->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/datetime/jquery.datetimepicker.css'/>" />


</head>

<body>
	<div class="container-scroller">
		<!-- partial:../../partials/_navbar.html -->

		<nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
			<div class="navbar-brand-wrapper d-flex justify-content-center">
				<div
					class="navbar-brand-inner-wrapper d-flex justify-content-between align-items-center w-100">
					<a class="navbar-brand brand-logo" href="index.do"><img
						src="<c:url value='/resources/template/images/YF_logo_big.png'/>"
						alt="logo" /></a> <a class="navbar-brand brand-logo-mini"
						href="index.do"><img
						src="<c:url value='/resources/template/images/YF_logo_small.png'/>"
						alt="logo" /></a>
					<button class="navbar-toggler navbar-toggler align-self-center"
						type="button" data-toggle="minimize">
						<span class="mdi mdi-sort-variant"></span>
					</button>
				</div>
			</div>
			<div
				class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
				<ul class="navbar-nav mr-lg-4 w-100">
					<li class="nav-item nav-search d-none d-lg-block w-100">
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text" id="search"> <i
									class="mdi mdi-magnify"></i>
								</span>
							</div>
							<form action="https://www.youtube.com/results?search_query=%23띵동">
							<input type="text" class="form-control" placeholder="태그를 검색하세요 빨리"
								aria-label="search" aria-describedby="search">
							</form>
						</div>
					</li>
				</ul>
				<ul class="navbar-nav navbar-nav-right">


					<li class="nav-item nav-profile dropdown"><a
						class="nav-link dropdown-toggle" href="#" data-toggle="dropdown"
						id="profileDropdown"> <c:choose>
								<c:when test="${userData.getUserClass() == 0}">
									<span class="nav-profile-name text-primary"><i class="mdi mdi-face"></i>${userData.getUserName()}님 로그인중</span>
								</c:when>
								<c:when test="${userData.getUserClass() == 1}">
									<span class="nav-profile-name text-primary">[ Admin <i class="mdi mdi-sunglasses"></i>] ${userData.getUserName()}님 로그인중</span>
								</c:when>

								<c:otherwise>
									<span class="nav-profile-name">Log Out</span>
								</c:otherwise>
							</c:choose>

					</a>
						<div class="dropdown-menu dropdown-menu-right navbar-dropdown"
							aria-labelledby="profileDropdown">
							<c:choose>
								<c:when test="${userData.getUserClass() == 0 ||userData.getUserClass() == 1}">
									<a class="dropdown-item"  href="#modifyDialog" data-toggle="modal"> <i
										class="mdi mdi-settings text-primary"></i> Modify
									</a>
									<a class="dropdown-item" href="logout.ing"> <i
										class="mdi mdi-logout text-primary"></i> Logout
									</a>
								</c:when>

								<c:otherwise>


									<a class="dropdown-item" href="#signupDialog"
										data-toggle="modal"> <i
										class="mdi mdi-account-plus text-primary"></i> Sign Up
									</a>
									<a class="dropdown-item" data-toggle="modal"
										href="#loginDialog"> <i class="mdi mdi-login text-primary"></i>
										Log In
									</a>
								</c:otherwise>
							</c:choose>

						</div></li>
				</ul>
				<button
					class="navbar-toggler navbar-toggler-right d-lg-none align-self-center"
					type="button" data-toggle="offcanvas">
					<span class="mdi mdi-menu"></span>
				</button>
			</div>
		</nav>

		<!-- partial -->
		<!-- 왼쪽 사이드바 ********************************************-->
		<div class="container-fluid page-body-wrapper">
			<!-- partial:../../partials/_sidebar.html -->
			<nav class="sidebar sidebar-offcanvas" id="sidebar">
				<ul class="nav">
					
					<li class="nav-item"><a class="nav-link" href="posting.do" >
							<i class="mdi mdi-bullhorn menu-icon"></i> <span
							class="menu-title">공지사항</span>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						data-toggle="collapse" href="#channel" aria-expanded="false"
						aria-controls="channel"> <i
							class="mdi mdi-television-guide menu-icon"></i> <span
							class="menu-title">채널</span> <i class="menu-arrow"></i>
					</a>
						<div class="collapse" id="channel">
							<ul class="nav flex-column sub-menu">
								<li class="nav-item"><a class="nav-link"
									href="channel.do">신규 채널보기</a></li>
								<li class="nav-item"><a class="nav-link"
									href="channel_my.do">선호 채널 보기</a></li>
							</ul>
						</div></li>
					<li class="nav-item"><a class="nav-link"
						data-toggle="collapse" href="#tag" aria-expanded="false"
						aria-controls="tag"> <i class="mdi mdi-tag menu-icon"></i> <span
							class="menu-title">태그</span> <i class="menu-arrow"></i>
					</a>
						<div class="collapse" id="tag">
							<ul class="nav flex-column sub-menu">
								<li class="nav-item"><a class="nav-link" href="tag_my.do">선호
										태그 관리</a></li>
								<li class="nav-item"><a class="nav-link" href="tag_my.do">내가
										단 태그 보기</a></li>
							</ul>
						</div></li>
					<li class="nav-item"><a class="nav-link" href="mypage.do">
							<i class="mdi mdi-account menu-icon"></i> <span
							class="menu-title">사용자 관리</span>
					</a></li>

				</ul>
			</nav>
			<!-- partial -->
			<div class="main-panel">
				<!-- 여기부터 바디 부분 ************************************************-->

				<div class="content-wrapper">
				<!-- 텝 카드 -->
				<div class="row">
            <div class="col-md-12 grid-margin stretch-card">
              <div class="card">
                <div class="card-body dashboard-tabs p-0">
                  <ul class="nav nav-tabs px-4" role="tablist">
                    <li class="nav-item">
                      <a class="nav-link active" id="overview-tab" data-toggle="tab" href="#overview" role="tab" aria-controls="overview" aria-selected="true">인기 태그 TOP5</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" id="sales-tab" data-toggle="tab" href="#sales" role="tab" aria-controls="sales" aria-selected="false">주간 인기 태그 TOP5</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" id="purchases-tab" data-toggle="tab" href="#purchases" role="tab" aria-controls="purchases" aria-selected="false">새로 업데이트된 채널</a>
                    </li>
                  </ul>
                 
                  <div class="tab-content py-0 px-0">
                    <div class="tab-pane fade show active" id="overview" role="tabpanel" aria-labelledby="overview-tab">
                      <div class="d-flex flex-wrap justify-content-xl-between">
                      
                       <c:forEach items="${tagdtoHots}" var="tag">
                       
                        <div class="d-flex border-md-right flex-grow-1 align-items-center justify-content-center p-3 item">
                        <c:choose>
                        	<c:when test="${tag.tagId == 1}">
                          		<i class="mdi mdi-numeric-1-box mr-3 icon-lg text-danger"></i>
                        	</c:when>
                        	<c:when test="${tag.tagId == 2}">
                          		<i class="mdi mdi-numeric-2-box mr-3 icon-lg text-warning"></i>
                        	</c:when>
                        	<c:when test="${tag.tagId == 3}">
                        		<i class="mdi mdi-numeric-3-box mr-3 icon-lg text-success"></i>
                        	</c:when>
                        	<c:when test="${tag.tagId == 4}">
                        		<i class="mdi mdi-numeric-4-box mr-3 icon-lg text-primary"></i>
                        	</c:when>
                        	<c:when test="${tag.tagId == 5}">
                        		<i class="mdi mdi-numeric-5-box mr-3 icon-lg text-info"></i>
                        	</c:when>
                        </c:choose>
                          
                          <div class="d-flex flex-column justify-content-around">
                            <h5 class="mb-1 text-muted"><a href="https://www.youtube.com/results?search_query=%23${tag.tagName}" target="_blank">#${tag.tagName}</a></h5>
                            <small class="mr-2 mb-0">${tag.tagCount}회</small>
                          </div>
                        </div>
                        </c:forEach>
                      </div>
                    </div>
                    
                    <div class="tab-pane fade" id="sales" role="tabpanel" aria-labelledby="sales-tab">
                      <div class="d-flex flex-wrap justify-content-xl-between">
                        
                       <c:forEach items="${tagdtoWeeks}" var="tag">
                       
                        <div class="d-flex border-md-right flex-grow-1 align-items-center justify-content-center p-3 item">
                        <c:choose>
                        	<c:when test="${tag.tagId == 1}">
                          		<i class="mdi mdi-numeric-1-box mr-3 icon-lg text-danger"></i>
                        	</c:when>
                        	<c:when test="${tag.tagId == 2}">
                          		<i class="mdi mdi-numeric-2-box mr-3 icon-lg text-warning"></i>
                        	</c:when>
                        	<c:when test="${tag.tagId == 3}">
                        		<i class="mdi mdi-numeric-3-box mr-3 icon-lg text-success"></i>
                        	</c:when>
                        	<c:when test="${tag.tagId == 4}">
                        		<i class="mdi mdi-numeric-4-box mr-3 icon-lg text-primary"></i>
                        	</c:when>
                        	<c:when test="${tag.tagId == 5}">
                        		<i class="mdi mdi-numeric-5-box mr-3 icon-lg text-info"></i>
                        	</c:when>
                        </c:choose>
                          
                          <div class="d-flex flex-column justify-content-around">
                            <h5 class="mb-1 text-muted"><a href="https://www.youtube.com/results?search_query=%23${tag.tagName}" target="_blank">#${tag.tagName}</a></h5>
                            <small class="mr-2 mb-0">${tag.tagCount}회</small>
                          </div>
                        </div>
                        </c:forEach>
                      </div>
                    </div>
                    <div class="tab-pane fade" id="purchases" role="tabpanel" aria-labelledby="purchases-tab">
                      <div class="d-flex flex-wrap justify-content-xl-between">
                        <c:forEach items="${chndtos}" var="channel">
                          <div class="d-flex border-md-right flex-grow-1 align-items-center justify-content-center p-3 item">
                          		<i class="mdi mdi-new-box mr-3 icon-lg text-warning"></i>
                        	<div class="d-flex flex-column justify-content-around">
                            	<h5 class="mb-1 text-muted"><a href="https://www.youtube.com/results?search_query=${channel.channelName}" target="_blank">${channel.channelName}</a></h5>
                            </div>
                          </div>
                        </c:forEach>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
				
				<!-- 텝카드 끝 -->
				
				<!-- 2번쨰 줄 시작 -->
				<div class="row">
				<!-- 공지사항 시작 -->
            <div class="col-md-6 stretch-card">
              <div class="card">
                <div class="card-body">
                  <p class="card-title">공지사항</p>
                  <div class="table-responsive">
                    <table id="recent-purchases-listing" class="table">
                      <thead>
                        <tr>
                            <th></th>
                            <th>제목</th>
                            <th>ID</th>
                            <th>등록일시</th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${postdtos}" var="post">
                        <tr>
                            <td>${post.postId}</td>
                            <td><a href="#" onclick="javascript:location.href='postView.do?recodeNum=${post.postId}'" >${post.postTitle}</a></td>
                            <td>${post.userId}</td>
                            <td>${post.postingDate}</td>
                        </tr>
                        </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
            <!-- 공지사항 끝 -->
            
            <!-- 나의 태그 활동 시작 -->
            <c:if test="${userData.getUserName() != null}">
            
            <div class="col-md-6 stretch-card">
              <div class="card">
                <div class="card-body">
                  <p class="card-title">나의 최근 태그 활동</p>
                  <div class="table-responsive">
                    <table id="recent-purchases-listing" class="table">
                      <thead>
                        <tr>
                            <th></th>
                            <th>채널명</th>
                            <th>태그명</th>
                            <th>ID</th>
                            <th>등록일시</th>
                        </tr>
                      </thead>
                      <tbody>
                      	<c:forEach items="${MytagDTOs}" var="tag">
											<tr>
												<td>${tag.tagId}</td>
												<td>${tag.channelId}</td>
												<td>#${tag.tagName}</td>
												<td>${tag.userId}</td>
												<td>${tag.taggingDate}</td>
											</tr>
                      	</c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
            </c:if>
            <c:if test="${userData.getUserName() == null}">
            
            <div class="col-md-6 stretch-card">
              <div class="card">
                <div class="card-body">
                <p class="card-title"><h1 class="display1">저희 페이지가 처음이신가요?</h1></p>
                <p class="card-description">아래의 Step을 따라해보세요</p>
                	<div class="table-responsive">
                  		<ul class="list-ticked">
                  			<li>회원가입을 하세요</li>
                  			<li>로그인을 하시고</li>
                  			<li>자신이 좋아하는 채널에 좋아요를 하세요</li>
                  			<li>채널에 태그를 달아보세요</li>
                  			<li>선호 채널그룹을 설정하세요</li>
                  			<li>선호 채널로 검색해보세요</li>
                  		</ul>
                  	</div>
                </div>
              </div>
            </div>
            </c:if>
            
            
            
            
            
          </div>
          	<!-- 2번째 줄 끝 -->
				
				
				
				
				
          
				
				<!-- 공지사항 끝 -->
				<!-- 여기까지가 바디의 끝  -->
				</div>
				<!-- content-wrapper ends -->
				<!-- partial:../../partials/_footer.html -->



				<footer class="footer">
					<div
						class="d-sm-flex justify-content-center justify-content-sm-between">
						<span
							class="text-muted text-center text-sm-left d-block d-sm-inline-block">Copyright
							© 2019 <a href="https://www.urbanui.com/" target="_blank">JiYong.K</a>.
							All rights reserved.
						</span> <span
							class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Hand-crafted
							& made with <i class="mdi mdi-heart text-danger"></i>
						</span>
					</div>
				</footer>



				<!-- partial -->




			</div>
			<!-- main-panel ends -->
		</div>
		<!-- page-body-wrapper ends -->




	</div>
	<!-- container-scroller -->




	<!-- 로그인 다이얼로그 창 -->
	<div class="modal fade" id="loginDialog" tabindex="-1" role="dialog"
		aria-labelledby="loginDialogLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="loginDialogLabel">Log In</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">X</span>
					</button>
				</div>
				<div class="modal-body">
				<form action="login.ing" method="post">
					
						<table class="table">
							<tr>
								<td>사용자 ID</td>
								<td><input type="text" name="userId" class="form-control"
									placeholder="ID를 입력" /></td>
							</tr>
							<tr>
								<td>PassWord</td>
								<td><input type="password" name="userPw"
									class="form-control" placeholder="PassWord를 입력" /></td>
							</tr>
						</table>
						<div class="modal-footer">
							<button class="btn btn-secondary" type="button"
								data-dismiss="modal">닫기</button>
							<button class="btn btn-primary" type="submit">로그인</button>
						</div>
				</form>
			</div>
		</div>
	</div>
	</div>
	<!-- 다이얼로그 -->
	<!-- 회원가입 다이얼로그 창 -->
	<div class="modal fade" id="signupDialog" tabindex="-1" role="dialog"
		aria-labelledby="signupDialogLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="signupDialogLabel">SignUp</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">X</span>
					</button>
				</div>
				<div class="modal-body">
				<form action="signup.ing" method="post">
					
						<table class="table">
							<tr>
								<td>사용자 ID</td>
								<td><input type="text" name="userId" class="form-control"
									placeholder="사용할 ID를 입력" /></td>
							</tr>
							<tr>
								<td>사용자 이름</td>
								<td><input type="text" name="userName" class="form-control"
									placeholder="사용할 이름을 입력" /></td>
							</tr>
							<tr>
								<td>PassWord</td>
								<td><input type="password" name="userPw"
									class="form-control" placeholder="사용할 PassWord를 입력" /></td>
							</tr>
							<tr>
								<td>Email</td>
								<td><input type="email" name="userEmail"
									class="form-control" placeholder="사용할 Email을 입력" /></td>
							</tr>

							<tr>
								<td colspan="2">성별</td>
							<tr>
								<td>
									<div class="form-check">
										<label class="form-check-label"> <input type="radio"
											class="form-check-input" name="userGender"
											id="optionsRadios2" value="1" checked> 여성
										</label>
									</div>
								</td>

								<td>
									<div class="form-check">
										<label class="form-check-label"> <input type="radio"
											class="form-check-input" name="userGender"
											id="optionsRadios2" value="2"> 남성
										</label>
									</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>생년월일</td>
								<td><input type="text" name="userBirth"
									class="form-control" id="userBirth" placeholder="이곳을 클릭해서 입력" /></td>
							</tr>

						</table>



						<div class="modal-footer">
							<button class="btn btn-secondary" type="button"
								data-dismiss="modal">닫기</button>
							<button class="btn btn-primary" type="submit">회원가입</button>
						</div>
				</form>
			</div>
		</div>
	</div>
	</div>
	<!-- 다이얼로그 -->
	<!-- 다이얼로그 -->
	<!-- 회원수정 다이얼로그 창 -->
	<div class="modal fade" id="modifyDialog" tabindex="-1" role="dialog"
		aria-labelledby="modifyDialogLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modifyDialogLabel">Modify</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">X</span>
					</button>
				</div>
				<div class="modal-body">
				<form action="userModify.ing" method="post">
						
						<table class="table">
							<tr>
								<td>사용자 ID</td>
								<td><input type="text" name="userId" class="form-control"
									value="${userData.getUserId()}" readonly/></td>
							</tr>
							<tr>
								<td>사용자 이름</td>
								<td><input type="text" name="userName" class="form-control" placeholder="사용할 이름을 입력"
									value="${userData.getUserName()}" /></td>
							</tr>
							<tr>
								<td>기존 PassWord</td>
								<td><input type="password" name="userPw"
									class="form-control" placeholder="사용중인 PassWord를 입력" /></td>
							</tr>
							<tr>
								<td>변경 PassWord</td>
								<td><input type="password" name="userPw2"
									class="form-control" placeholder="사용변경할  PassWord를 입력" /></td>
							</tr>
							<tr>
								<td>Email</td>
								<td><input type="email" name="userEmail"
									class="form-control" placeholder="사용할 Email을 입력" value="${userData.getUserEmail()}"/></td>
							</tr>
							
							<tr>
								<td colspan="2">성별</td>
							<tr>
								<c:choose>
									<c:when test="${userData.getUserGender() == 1}">
									<td>
									<div class="form-check">
										<label class="form-check-label"> <input type="radio"
											class="form-check-input" name="userGender"
											id="optionsRadios2" value="1" checked> 여성
										</label>
									</div>
								</td>

								<td>
									<div class="form-check">
										<label class="form-check-label"> <input type="radio"
											class="form-check-input" name="userGender"
											id="optionsRadios2" value="2"> 남성
										</label>
									</div>
								</td>
									</c:when>
									
									<c:when test="${userData.getUserGender() == 2}">
									<td>
									<div class="form-check">
										<label class="form-check-label"> <input type="radio"
											class="form-check-input" name="userGender"
											id="optionsRadios2" value="1"> 여성
										</label>
									</div>
								</td>

								<td>
									<div class="form-check">
										<label class="form-check-label"> <input type="radio"
											class="form-check-input" name="userGender"
											id="optionsRadios2" value="2" checked> 남성
										</label>
									</div>
								</td>
									</c:when>
									
								</c:choose>
								
							</tr>
							<tr>
								<td>생년월일</td>
								<td><input type="text" name="userBirth"
									class="form-control" id="userBirth" placeholder="이곳을 클릭해서 입력" value="${userData.getUserBirth()}"/></td>
							</tr>

						</table>



						<div class="modal-footer">
							<button class="btn btn-secondary" type="button"
								data-dismiss="modal">닫기</button>
							<button class="btn btn-primary" type="submit">정보수정</button>
						</div>
				</form>
			</div>
		</div>
	</div>
	</div>
	<!-- 다이얼로그 -->





	<!-- plugins:js -->
	<script
		src="<c:url value='/resources/template/vendors/base/vendor.bundle.base.js'/>"></script>
	<!-- endinject -->
	<!-- Plugin js for this page-->
	<script
		src="<c:url value='/resources/template/vendors/chart.js/Chart.min.js'/>"></script>
		<script src="<c:url value='/resources/template/vendors/datatables.net/jquery.dataTables.js'/>"></script>
	<!-- End plugin js for this page-->
	<!-- inject:js -->
	<script src="<c:url value='/resources/template/js/off-canvas.js'/>"></script>
	<script
		src="<c:url value='/resources/template/js/hoverable-collapse.js'/>"></script>
	<script src="<c:url value='/resources/template/js/template.js'/>"></script>
	<!-- endinject -->
	<!-- Custom js for this page-->
	<script src="<c:url value='/resources/template/js/chart.js'/>"></script>
	<!-- DateTimePicker -->
	<script src="<c:url value='/resources/datetime/jquery.js'/>"></script>
	<script
		src="<c:url value='/resources/datetime/build/jquery.datetimepicker.full.js'/>"></script>

	<script>
		jQuery(document).ready(
				function() {
					jQuery('#userBirth,#userBirth2').datetimepicker(
							{
								i18n : {
									de : {
										months : [ 'Januar', 'Februar', 'März',
												'April', 'Mai', 'Juni', 'Juli',
												'August', 'September',
												'Oktober', 'November',
												'Dezember', ],
										dayOfWeek : [ "So.", "Mo", "Di", "Mi",
												"Do", "Fr", "Sa.", ]
									}
								},
								timepicker : false,
								format : 'Y-m-d'
							});
				});
	</script>
	<!-- End custom js for this page-->
</body>

</html>
