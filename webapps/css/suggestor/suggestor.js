(function(a){a.fn.suggestor=function(m){var f;var d;var s="";var j=false;var i=0;var h=0;var l=false;var c=false;var r=5;var n=1;var o;var k;var b;var p;var q;var g;var t=false;var e={init:function(u,w){t=false;f=a("<div/>");f.addClass("suggestorBox");f.css("display","none");f.css("position","absolute");f.css("text-align","left");f.css("font-size",u.css("font-size"));if(typeof w.boxCssInfo==="undefined"){f.css("border","1px solid #cccccc");f.css("-webkit-box-shadow","0 3px 2px 0px rgba(0, 0, 0, 0.1), 0 3px 2px 0px rgba(236, 236, 236, 0.6)");f.css("-moz-box-shadow","0 3px 2px 0px rgba(0, 0, 0, 0.1), 0 3px 2px 0px rgba(236, 236, 236, 0.6)");f.css("box-shadow","0 3px 2px 0px rgba(0, 0, 0, 0.1), 0 3px 2px 0px rgba(236, 236, 236, 0.6)");f.css("background-color","#fff")}else{f.css(w.boxCssInfo)}d=u;d.attr("autocomplete","off");j=false;s=d.val();o=w.ajaxinfo;n=w.minturm;b=w.searchForm;p=w.listSelectedCssInfo;q=w.listDeselectedCssInfo;k=w.adjustWidthVal;g=w.boxCssInfo;f.hover(function(){l=true},function(){l=false});this.resize();var v=this;a(window).resize(function(){v.resize()});a("body").append(f)},suggest:function(){t=true;this.resize();var u=this;s=d.val();i=0;h=0;if(s.length<n){f.css("display","none");t=false;return}a.ajax({url:o.url,type:"get",dataType:"jsonp",cache:false,data:{query:d.val(),type:"suggest",fn:o.fn,num:o.num*2}}).done(function(v){u.createAutoCompleteList(v)}).fail(function(w,x,v){t=false;return})},createAutoCompleteList:function(z){var x=z.response;var v=this;var G=0;i=0;if(typeof x.result!=="undefined"){var A=new Array();for(var B=0;B<x.result.length;B++){var D=x.result[B].result;for(var y=0;y<D.length;y++){A.push(D[y])}}var u=a("<ol/>");u.css("list-style","none");u.css("padding","0");u.css("margin","2px");for(var B=0;B<A.length&&i<o.num;B++){var F=A[B];var C=true;if(F===d.val()){C=false}else{var E=a(u.children("li"));for(var y=0;y<E.size();y++){if(F==a(E.get(y)).html()){C=false}}}if(C){var w=a("<li/>");w.html(F);w.click(function(){var H=a(this).html();v.fixList();d.val(H);if(typeof b!=="undefined"){b.submit()}});w.hover(function(){h=a(this).closest("ol").children("li").index(this)+1;a(this).closest("ol").children("li").each(function(H){if(H==(h-1)){if(typeof p==="undefined"){a(this).css("background-color","#e5e5e5")}else{a(this).css(p)}}else{if(typeof q!=="undefined"){a(this).css(q)}else{if(typeof g==="undefined"||typeof g["background-color"]==="undefined"){a(this).css("background-color","#ffffff")}else{a(this).css("background-color",g["background-color"])}}}})},function(){if(h==(a(this).closest("ol").children("li").index(this)+1)){if(typeof q!=="undefined"){a(this).css(q)}else{if(typeof g==="undefined"||typeof g["background-color"]==="undefined"){a(this).css("background-color","#ffffff")}else{a(this).css("background-color",g["background-color"])}}h=0}});w.css("padding","2px");u.append(w);i++}}if(i>0&&d.val().length>=n){f.html("");f.append(u);f.css("display","block")}else{f.css("display","none")}}else{f.css("display","none")}this.resize();t=false},selectlist:function(v){if(f.css("display")=="none"){return}if(v=="down"){h++}else{if(v=="up"){h--}else{return}}j=true;if(h<0){h=i}else{if(h>i){h=0}}var u=f.children("ol").children("li");f.children("ol").children("li").each(function(w){if(w==(h-1)){if(typeof p==="undefined"){a(this).css("background-color","#e5e5e5")}else{a(this).css(p)}d.val(a(this).html())}else{if(typeof q!=="undefined"){a(this).css(q)}else{if(typeof g==="undefined"||typeof g["background-color"]==="undefined"){a(this).css("background-color","#ffffff")}else{a(this).css("background-color",g["background-color"])}}}});if(h==0){d.val(s)}},fixList:function(){if(h>0){d.val(a(f.children("ol").children("li").get(h-1)).html())}s=d.val();j=false;f.css("display","none");i=0},resize:function(){f.css("top",d.offset().top+d.height()+6);f.css("left",d.offset().left);f.css("height","auto");f.css("width","auto");if(f.width()<d.width()+k){f.width(d.width()+k)}}};e.init(a(this),m);a(this).keydown(function(u){if(((u.keyCode>=48)&&(u.keyCode<=90))||((u.keyCode>=96)&&(u.keyCode<=105))||((u.keyCode>=186)&&(u.keyCode<=226))||u.keyCode==8||u.keyCode==32||u.keyCode==46){c=true;j=false}else{if(u.keyCode==38){if(f.css("display")!="none"){u.preventDefault()}e.selectlist("up")}else{if(u.keyCode==40){if(f.css("display")=="none"){e.suggest()}else{e.selectlist("down")}}else{if(u.keyCode==13){if(j){e.fixList()}}}}}});a(this).keyup(function(u){if(((u.keyCode>=48)&&(u.keyCode<=90))||((u.keyCode>=96)&&(u.keyCode<=105))||((u.keyCode>=186)&&(u.keyCode<=226))||u.keyCode==8||u.keyCode==32||u.keyCode==46){c=true;j=false}else{if(u.keyCode==38){}}});a(this).blur(function(){if(!l){e.fixList()}});setInterval(function(){if(r<5){r=r+1}else{if(d.val()!=s){if(!j&&c&&!t){e.suggest();r=0}}}},100)}})(jQuery);