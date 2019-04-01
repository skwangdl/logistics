?/**
 * Forbid to drag item on screen.
 */
function prohibitDragDrop() {
    event.dataTransfer.effectAllowed = "none";
}
if (document.all) {
    document.ondragstart = prohibitDragDrop;
}
document.oncontextmenu = function() { return false;} 

/**
 * Check number.
 * Used in function [dateCheck]
 */
function numCheck(value) {
    len = value.length;
    decPoint = false;
    if (value == "-" || value == ".") {
        return false;
    }
    for (i = 0; i < len; i++) {
        c = value.charAt(i);
        switch (c) {
            case '0' :
            case '1' :
            case '2' :
            case '3' :
            case '4' :
            case '5' :
            case '6' :
            case '7' :
            case '8' :
            case '9' :
           //case ' ' :
                break;
            case '-' :
                if (i != 0) {
                    return false;
                }
                break;
            case '.' :
                if (decPoint == true) {
                    return false;
                }
                decPoint = true;
                break;
            default :
                return false;
        }
    }
    return true;
}

/**
 * Change data type to Int.
 * Used in function [dateCheck]
 */
function parseIntX(value) {
    str = allSpaceDel(value);
    len = str.length;
    for (i = 0; i < len; i++) {
        c = str.charAt(i);
        if (c != '0') {
            break;
        }
    }
    if (i >= len) {
        i = i - 1;
    }
    if ( i >= 0 ) {
        return parseInt(str.substring(i,len));
    } else {
        return str;
    }
}

/**
 * Delete space.
 * Used in function [parseIntX]
 */
function allSpaceDel(value) {
    len = value.length;
    str = "";
    for (i = 0; i < len; i++) {
        c = value.charAt(i);
        if (c == ' ' || c == '　') {
        } else {
            str = str + c;
        }
    }
    return str;
}

/**
 * Trim space.
 */
function trimX(value) {
    len = value.length;
    start = 0;
    end = value.length;
    for (i = 0; i < len; i++) {
        c = value.charAt(i);
        if (c == " ") {
            start++;
        } else {
            break;
        }
    }   
	if(start == end){
		return "";
	}	
    for (j = value.length - 1; j > 0; j--) {
        c = value.charAt(j);
         if (c == " ") {
            end--;
        } else {
            break;
        }
    }
    return value.substring(start, end);
}

//Trim 全半角 space
function delFirstLastSpace( str ){
	var i
 	if( str.match("[ 　]$") != null ){
  		while(1){
  	 		if( str.match("[ 　]$") == null )break;
   				strNew = str.substring(0,(str.length-1));
   				str = strNew;
  		}
 	}
 	for(i=0;i<str.length;i++){
  		if( str.charAt(i) !=" "&&str.charAt(i) != "　"){
   			break;
  		}
 	}
 	str = str.substring(i);
 	return str
}

function createPassword() {
	var nowDate = new Date();
	return Math.random() * 10000000000000000000 + nowDate.getTime();
}

/**************************************************************
 * 関数名		：checkDateFromTo(fromDate,toDate)
 * 引数			：From、To日付、flg
 * 戻り値			：無し
 * 機能説明		：日付のFrom、Toのチェック
 * 				：日付は正しい書式(yyyy/mm/dd)
 * 				：checkDateFromTo("2006/12/01","2006/12/01") true
 * 				：checkDateFromTo("2006/12/01","2006/12/02") true
 * 				：checkDateFromTo("2006/12/01","2006/11/30") false
 * 				：checkDateFromTo("2006/12/01","2006/12/01","1") false
 * 				：checkDateFromTo("2006/12/01","2006/12/02","1") true
 * 				：checkDateFromTo("2006/12/01","2006/11/30","1") false
 **************************************************************/
function checkDateFromTo(fromDate,toDate,flg) {
	if ( "" == fromDate || "" == toDate ) { 
		return true; 
	}
	fd = fromDate.split("/");
	td = toDate.split("/");
	for( i = 0 ; i < 3 ; i++ ) {
		if ( eval(fd[i]) < eval(td[i]) ) { 
			return true;
		} else if(eval(fd[i]) > eval(td[i])) {
			return false;
		}
	}
	if (arguments.length == 3) {
		return false;
	}

	return true;
}

/**
 * 文字のByte数を算出
 * @param str
 * @returns 文字のByte数
 */
function getByte(str) {
	// バイト数を格納する変数
	var count = 0;
	for (i = 0; i < str.length; i++){
		var checkKana = str.charAt(i);
		// 半角文字の場合
		if ((checkKana >= " " && checkKana <= "~")||(checkKana >= "｡" && checkKana <= "ﾟ")) {
		// 半角文字1バイトを足す
			count+=1;
		}else{
			// 全角文字の場合
			// 全角文字2バイトを足す
			count+=2;
		}
	}
	// バイト数を返す
	return count;
}

/**
 * ファイル拡張子チェック
 * @param fileName
 */
function checkFileSuffix(fileName, fileSuffixAll){
	var checkResult = false;
	var fileSuffix = getFileSuffix(fileName);
	fileSuffixAll = fileSuffixAll.replace(/\n/,"");
	var fileSuffixArr = fileSuffixAll.toUpperCase().split(",");
	for (i=0;i<fileSuffixArr.length ;i++ ) {
	    if (fileSuffix.toUpperCase() === fileSuffixArr[i]) {
	    	checkResult = true;
	    }
	}
	return checkResult;
}

/**
 * ファイル拡張子を取得
 * @param fileName
 * @returns
 */
function getFileSuffix(fileName) {
    var ret;
    if (!fileName) {
    	return ret;
    }
    var fileTypes = fileName.split(".");
    var len = fileTypes.length;
    if (len === 0) {
    	return ret;
    }
    ret = fileTypes[len - 1];
    return ret;
}

/**
 * 最大長さチェック
 */
function checkstr(str,len) {    
	num=getByte(str);
	if(num > len){
		return false;
	} else{
		return true;
	}
}

/**
 * 添付ファイルチェック
 * @param fileDom
 * @param acceptType
 * @param nameMaxLength
 * @param maxSize MB
 * @param necessaryFlag
 * @param name
 * @returns
 */
function checkFile(fileDom, acceptType, nameMaxLength, maxSize, necessaryFlag, name) {
	
	var filePath = fileDom.value;
	
	// 必須指定フラグ
	if (necessaryFlag == "1"){
		if (filePath == ""){
			window.top.showAlertMsg("MSGOE012", name);
			fileDom.focus();
			return false;
		}
	}
	
	// ファイルパスチェック
	if(filePath != "" && filePath.match(/^[a-zA-Z]:\\/) == null) {
		window.top.showAlertMsg("MSGOE019", name);
		fileDom.focus();
		return false;
	}

	if(filePath){
		var file = fileDom.files[0];
		var fileName = file.name;
		
		// ファイル類型チェック
		if (!checkFileSuffix(fileName, acceptType)){
			window.top.showAlertMsg("MSGOE021", name, acceptType);
			fileDom.focus();
			return false;
		}
		
		// ファイル名文字数チェック
		if (getByte(fileName) > nameMaxLength) {
			window.top.showAlertMsg("MSGOE023", name, nameMaxLength);
			fileDom.focus();
			return false;
		}
		
		var fileSize = file.size;
		// 空ファイルチェック
		if (fileSize == 0){
			window.top.showAlertMsg("MSGOE022", name);
			fileDom.focus();
			return false;
		}
		
		// ファイルサイズチェック
		if (fileSize > maxSize * 1024 * 1024){
			window.top.showAlertMsg("MSGOE020", name, maxSize);
			fileDom.focus();
			return false;
		}
	}
	return true;
}

// 重複サブミット制御
function repeatSubmitCtrl(formId){
	$("#" + formId + " input[type='button']").attr("disabled",true);
	$("#" + formId + " button").attr("disabled",true);		
}

//　項目前後スペースを除く
function trimLRAll(){
	$("input:text").not("[readonly]").each(function(index, item){
		if (!item.disabled){
			var str = $(this).val(); 
			str = delFirstLastSpace(str); 
			$(this).val(str);   
		}
	});
}

//連動リストを取得
function linkageList(actionNm, formId, toCode, list, fromCode){
	var url = $("#contextPath").val() + "/" + actionNm + "_" + list + ".do";
	var parm = {};
	parm[formId + '.' + fromCode] = $("#" + fromCode).val();
	$.post(
		url,
		parm,
		function(data){
			$("#" + toCode).replaceWith(data);
			return false;
		}
	);
}