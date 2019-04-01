<div class="row">
	<div id="leftMenu">
		<div class="btn-group navbar-fixed-top" style="left: auto;">
		    <button type="button" class="btn btn-primary dropdown-toggle btn-xs" data-toggle="dropdown">
				<span class="glyphicon glyphicon-bell" />
		        <span class="caret"></span>
		    </button>
		    <ul class="dropdown-menu" role="menu" style="left: -115px;">
				<c:forEach items="${errors }" var="error" varStatus="errorStatus">
					<li>${error }</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>