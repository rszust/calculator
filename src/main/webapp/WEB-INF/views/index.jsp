<html>
<head>
<link rel="stylesheet" href="./css/center.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
</head>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.0-beta.0/angular.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<body ng-app="cinemaCityCalculator">

	<div class="container center-block"
		ng-controller="cinemaCityCalculatorController">

		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<div class="navbar-collapse collapse navbar-right">
					<ul class="nav navbar-nav">
						<li><a href="#about" ng-click="showHistory = !showHistory">History</a></li>
						<li><a href="#contact" ng-click="showHelp = !showHelp">Help</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</nav>

		<div class="template">
			<div class="center-block" align="center">
				<h4>{{result}}</h4>
			</div>
			<div role="alert" class="alert alert-danger"
				ng-show="showAlert && expressionForm.$invalid">
				<span class="error"
					ng-show="expressionForm.expressionInput.$error.minlength">
					Expression must have at least one character.</span> <span class="error"
					ng-show="expressionForm.expressionInput.$error.required">
					Expression is required.</span>
			</div>
			<form name="expressionForm"
				ng-submit="calculate(expressionForm.$valid)" novalidate>
				<div class="form-group">
					<label for="expressionInput">Expression</label> <input
						id="expressionInput" name="expressionInput" type="text"
						ng-model="request" class="form-control"
						placeholder="Enter the expression..." ng-minlength="3"
						ng-required="true" ng-trim="true" />
				</div>
				<div class="form-group">
					<button type="submit" title="Calculate" class="btn btn-default">
						<span>Calculate</span>
					</button>
				</div>
			</form>
		</div>
		<div class="template">
			<div class="well" ng-hide="!showHelp">
				<p>
				<ul>
				    <li>Two or more spacedsd between expression element is treated as error.</li>
					<li>All expression elements must be followed/preceded by
						whitespace</li>
					<li>Allowed operators are: + - / *</li>
					<li>Allowed functions are: sqr sqrt</li>
				</ul>
				</p>
				<p>Examplary usages are:</p>
				<ul>
					<li>-4 / 4 or -4 + -5 or 9 * 3 or 9 / 3</li>
					<li>sqrt ( sqr ( 3 ) ) or sqr ( 3 * 3 )</li>
					<li>{ 3 + 4 } * [ 4 + 3 ] * { 6 + 3 } or [ { 3 + 4 } * ( 4 + 3
						) ] * { 6 + 3 }</li>
				</ul>
			</div>
			<div class="panel panel-default" ng-hide="showHistory">
				<div class="panel-heading">
					<h3 class="panel-title">Operations</h3>
				</div>
				<div class="panel-body">
					<table class="table table-striped table-bordered">
						<tr>
							<th>Timestamp</th>
							<th>Expression</th>
							<th>Result</th>
						</tr>
						<tr ng-repeat="item in history">
							<th>{{item.timestamp}}</th>
							<th>{{item.expression}}</th>
							<th>{{item.response}}</th>
						</tr>
					</table>
				</div>
			</div>

		</div>
	</div>
	<script>
		var app = angular.module('cinemaCityCalculator', []);
		app.controller('cinemaCityCalculatorController',
				function($scope, $http) {
					$scope.showAlert = false;
					$scope.showHistory = true;
					$scope.showHelp = false;
					$scope.history = [];
					$scope.calculate = function(isValid) {
						if (isValid) {
							$scope.showAlert = false;
							var calculateRequest = {
								expression : $scope.request,
								threads : null,
								precision : null
							};
							var res = $http.post(
									'http://localhost:8888/calculator',
									calculateRequest);
							res
									.success(function(data, status, headers,
											config) {
										$scope.result = data.expression + " = "
												+ data.response;
										$scope.history.splice(0, 0, {
											expression : data.expression,
											response : data.response,
											timestamp : data.timestamp
										});
									});
							res.error(function(data, status, headers, config) {
								alert("failure message: " + JSON.stringify({
									data : data
								}));
							});
						} else {
							$scope.showAlert = true;
						}
					}

				});
	</script>
</body>
</html>