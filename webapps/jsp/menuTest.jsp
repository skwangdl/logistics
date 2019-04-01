	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Menu tree</title>
	<link href="<%=request.getContextPath()%>/css/bootstrap/3.3.4/css/bootstrap.css" rel="stylesheet">
	<style type="text/css">
		.jq22-header{margin-bottom: 15px;font-family: "Segoe UI", "Lucida Grande", Helvetica, Arial, "Microsoft YaHei", FreeSans, Arimo, "Droid Sans", "wenquanyi micro hei", "Hiragino Sans GB", "Hiragino Sans GB W3", "FontAwesome", sans-serif;}
		.jq22-icon{color: #fff;}
		.list-group-item{background:#E7E8F7;}
	</style>
	<script>
	function itemOnclick(target){
		
		var nodeid = $(target).attr('data-nodeid'); 
		//tree
		var tree = $('#NationalPatent'); 
		//node
		var node = tree.treeview('getNode', nodeid); 
		if(node.state.expanded){
			//close node
			tree.treeview('collapseNode', node.nodeId); 
		} else {
			//open node
			tree.treeview('expandNode', node.nodeId); 
		}
	} 
	</script>
	<!-- <div class="jq22-container"> -->
		
		<div class="container" style="width: 250px; height:550px; border-right:1px solid #ccc;padding: 0px;">
      <div class="a">
        <!-- <div class="">
          <div id="NationalPatent" class=""></div>
        </div> -->
		          <div id="NationalPatent" class=""></div>
		
      </div>

      <br/>
      <br/>
      <br/>
      <br/>
    </div>
	    
		
	<!-- </div> -->
	
	<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap-treeview.js"></script>
	<script type="text/javascript">

  	//
    function getTree() {
        var tree = null;
        $.ajax({
            type: "post",
            url: "<%=request.getContextPath()%>/menu/getMenuList",
            async: false,
            dataType: 'json',
            data: {
            },
            success: function (data) {
                tree = data;
            }
        })
        console.log(tree);
        return tree;
    }
    

    $(function () {
		
        $('#NationalPatent').treeview({
            data: getTree(),
            showTags: true,
            enableLinks: true,
            levels: 1,
            showTags: true
        });
            <%-- ,onNodeSelected: function(event, data) {
               alert(data.nodeId);
               if (data.nodes === undefined || data.nodes === null) {
                   return;
               }
               $("#tree1").treeview("deleteNode", [data.nodeId, { silent: true }]);
               
           },onNodeExpanded:
        	   function(event, data) {
        	           $.ajax({
        	               type: "Post",
        	               url: "<%=request.getContextPath()%>/menu/getMenuListForChild",
        	               dataType: "json",
        	               success: function (result) {
        	                   for (var index = 0; index < result.length; index++) {
        	                       var item = result[index];
        	                       $("#tree1").treeview("addNode",
        	                           [
        	                               data.nodeId,
        	                               { node: { text: item.text, id: item.id }, silent: true }
        	                           ]);
        	                   }
        	               }
        	           });
        	   } 
        });--%>
	    
	    //
         <%-- $('#NationalPatent').on('nodeExpanded', function (event, data) {
            console.log(data);
            var id = data['nodeId'];
            console.log(id);
            $.ajax({
                type: "post",
                url: "<%=request.getContextPath()%>/menu/getMenuListForChild",
                async: false,
                dataType: 'json',
                data: {
                    PIPC: data['text']
                },
                success: function (result) {
                	console.log(result);
                    $("#NationalPatent").treeview("deleteNode", id);	//
                    for (var i = 0; i < result.length; i++) {
                        $("#NationalPatent").treeview("addNode", [id, { node: result[i], silent: true}]); 
                    }
                }
            })
        });  --%>
    });
</script>