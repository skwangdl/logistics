//**********************************************************
//* 荷主選択ヘルプ
//* 
//* 引数
//* 荷主コード
//* 管理元会社区分選択フラグ(設定しない場合は選択可能)
//* 	０：管理元会社区分コンボ選択可能
//* 	１：管理元会社区分コンボ選択不可
//* 
//* 返り値
//* ０：荷主コード
//* １：荷主会社コード
//* ２：荷主名
//* ３：郵便番号
//* ４：都道府県コード
//* ５：市区町村コード
//* ６：住所１
//* ７：住所２
//* ８：住所３
//* ９：電話番号
//* １０：荷主契約区分
//* １１：契約事業区分
//* １２：配送契約区分
//* １３：引取返品契約区分
//* １４：回収契約区分
//* １５：得意先コード
//* １６：荷主カナ名
//* １７：荷主略称
//* １８：荷主検索名称
//* １９：担当者名
//* ２０：検索電話番号
//* ２１：ＦＡＸ番号
//* ２２：売上振替区分
//* ２３：売上変動費除外区分
//* ２４：荷送人名
//* ２５：荷送人カナ名
//* ２６：荷送人郵便番号
//* ２７：荷送人都道府県コード
//* ２８：荷送人市区町村コード
//* ２９：荷送人住所１
//* ３０：荷送人住所２
//* ３１：荷送人住所３
//* ３２：荷送人電話番号
//* ３３：荷送人検索電話番号
//* ３４：荷送人ＦＡＸ番号
//* ３５：荷送人備考
//* ３６：所属地域区分
//* ３７：運賃体系コード
//* ３８：割引体系コード
//* ３９：荷主割増体系コード
//* ４０：料金計算方式区分
//**********************************************************
function PLC1H0010_sel(ninushiCd, flg){
	//荷主ヘルプ表示
	var retData = new Array(41);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//実行パラメタ　1:実行urlパス　,2:荷主ＣＤ
	goparm= "../../," + ninushiCd + "," + flg;
	//入力検索文字チェック
	//ダイアログにより荷主選択ヘルプ表示
	//flag= window.showModalDialog("/logistics/contents/html/PLC1H0010Fram.html",goparm,"dialogHeight:600px; dialogWidth:860px; scroll:0; status:0; resizable:1;");
	flag= window.showModalDialog("/logistics/contents/html/PLC1H0010Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");
	
	if (flag==null || flag.length==0){
		//戻り値が空の場合は閉じるで戻った場合。
		return null;
	} else {
		//戻り値４１つ。
		retValue=flag + ",";
		for (i = 0; i < 41; i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}

// 2018/05/31 NTS_朱 【No.B502】問合せ一覧(クレーム/問題) の機能追加について 修正依頼。ADD Start
//**********************************************************
//* 削除確認ヘルプ
//**********************************************************
function PLC1H0170_sel(){
	var flag;
	flag= window.showModalDialog("/logistics/contents/html/PLC1H0170.html","","dialogWidth:260px;dialogHeight:150px;directories:no; localtion:no; menubar:no; status:0; toolbar:no;scrollbars:no;Resizeable:1;scroll:0;");
	
	if (flag==null || flag.length==0 || flag == "1"){
		//戻り値が空の場合は閉じるで戻った場合。
		return false;
	} else {
		return true;
	}
}
// 2018/05/31 NTS_朱 【No.B502】問合せ一覧(クレーム/問題) の機能追加について 修正依頼。ADD End
//**********************************************************
//* 業者選択ヘルプ
//* 
//* 引数
//* 業者コード
//* 管理元会社区分選択フラグ(設定しない場合は選択可)
//* 	０：管理元会社区分コンボ選択可
//* 	１：管理元会社区分コンボ選択不可
//* 
//* 返り値
//* ０：業者コード
//* １：業者名
//* ２：電話番号
//* ３：仕入先コード
//* ４：税区分
//* ５：契約開始日
//* ６：契約終了日
//**********************************************************
function PLC1H0020_sel(gyousyaCd, flg){
	//業者ヘルプ表示
	var retData = new Array(7);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//実行パラメタ　1:実行urlパス　,2:業者コード
	goparm= "../../," + gyousyaCd + "," + flg;
	//入力検索文字チェック
	//ダイアログにより業者選択ヘルプ表示
	//flag=window.showModalDialog("/logistics/contents/html/PLC1H0020Fram.html",goparm,"dialogHeight:600px; dialogWidth:860px; scroll:0; status:0; resizable:1;");
	flag=window.showModalDialog("../html/PLC1H0020Fram.html",goparm,"dialogHeight:630px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//戻り値が空の場合は閉じるで戻った場合。
		return null;
	} else {
		//戻り値７つ。
		retValue=flag + ",";
		for (i = 0;i<7;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}

//**********************************************************
//* ドライバー選択ヘルプ
//* 
//* 引数
//* ドライバーコード、リーダー区分、
//* 管理元会社区分選択フラグ(設定しない場合は選択可)
//* 	０：管理元会社区分コンボ選択可
//* 	１：管理元会社区分コンボ選択不可
//* 
//* 返り値
//* ０：ドライバーコード
//* １：ドライバー名
//* ２：業者コード
//* ３：業者名
//* ４：携帯電話番号
//* ５：リーダ区分
//* ６：契約事業区分
//* ７：所属区分
//* ８：デポコード
//**********************************************************
function PLC1H0030_sel(driverCd,leaderKb, flg){
	//ドライバーヘルプ表示
	var retData = new Array(9);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	leaderKb = "" + leaderKb;
	if (leaderKb == "undefined" || leaderKb == null){
		leaderKb = "";
	}
	//実行パラメタ　1:実行urlパス　,2:ドライバーコード　,3:リーダー区分
	goparm= "../../," + driverCd + "," + leaderKb + "," + flg;
	//入力検索文字チェック
	//ダイアログによりドライバー選択ヘルプ表示
	//flag=window.showModalDialog("/logistics/contents/html/PLC1H0030Fram.html",goparm,"dialogHeight:600px; dialogWidth:860px; scroll:0; status:0; resizable:1;");
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0030Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//戻り値が空の場合は閉じるで戻った場合。
		return null;
	} else {
		//戻り値９つ。
		retValue=flag + ",";
		for (i = 0;i<9;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}

// 2017/08/17 NTS_朱 No.309 ドライバーメンテナンス機能の改修 ADD Start
//**********************************************************
//* ドライバー選択ヘルプ
//* 
//* 引数
//* ドライバーコード、リーダー区分、
//* 管理元会社区分選択フラグ(設定しない場合は選択可)
//* 	０：管理元会社区分コンボ選択可
//* 	１：管理元会社区分コンボ選択不可
//* 
//* 返り値
//* ０：ドライバーコード
//* １：ドライバー名
//* ２：業者コード
//* ３：業者名
//* ４：携帯電話番号
//* ５：リーダ区分
//* ６：契約事業区分
//* ７：所属区分
//* ８：デポコード
//**********************************************************
function PLC1H0031_sel(driverCd,leaderKb, flg){
	//ドライバーヘルプ表示
	var retData = new Array(9);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	leaderKb = "" + leaderKb;
	if (leaderKb == "undefined" || leaderKb == null){
		leaderKb = "";
	}
	//実行パラメタ　1:実行urlパス　,2:ドライバーコード　,3:リーダー区分
	goparm= "../../," + driverCd + "," + leaderKb + "," + flg;
	//入力検索文字チェック
	//ダイアログによりドライバー選択ヘルプ表示
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0031Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//戻り値が空の場合は閉じるで戻った場合。
		return null;
	} else {
		//戻り値９つ。
		retValue=flag + ",";
		for (i = 0;i<9;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}
// 2017/08/17 NTS_朱 No.309 ドライバーメンテナンス機能の改修 ADD End
//**********************************************************
//* 住所選択ヘルプ
//* 
//* 引数
//* 郵便番号
//* 管理元会社区分選択フラグ(設定しない場合は選択可)
//* 	０：管理元会社区分コンボ選択可
//* 	１：管理元会社区分コンボ選択不可
//* 
//* 返り値
//* ０：郵便番号
//* １：住所ＣＤ
//* ２：都道府県名
//* ３：市区町村
//* ４：その他住所
//**********************************************************
function PLC1H0060_sel(jyusyoCd){
	//住所ヘルプ表示
	var retData = new Array(5);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//実行パラメタ　1:実行urlパス　,2:郵便番号
	goparm= "../../," + jyusyoCd;
	//入力検索文字チェック
	//ダイアログにより住所選択ヘルプ表示
//	flag=window.showModalDialog("/logistics/contents/html/PLC1H0060Fram.html",goparm,"dialogHeight:600px; dialogWidth:860px; scroll:0; status:0; resizable:1;");
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0060Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//戻り値が空の場合は閉じるで戻った場合。
		return null;
	} else {
		//戻り値５つ。
		retValue=flag + ",";
		for (i = 0;i<5;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}

//**********************************************************
//* 仕入先選択ヘルプ
//* 
//* 引数
//* 仕入先コード
//* 
//* 返り値
//* ０：仕入先コード
//* １：仕入先名
//* ２：電話番号
//**********************************************************
function PLC1H0070_sel(shiiresakiCd){
	//仕入先ヘルプ表示
	var retData = new Array(3);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//実行パラメタ　1:実行urlパス　,2:仕入先コード
	goparm= "../../," + shiiresakiCd;
	//入力検索文字チェック
	//ダイアログにより仕入先選択ヘルプ表示
	//flag=window.showModalDialog("/logistics/contents/html/PLC1H0070Fram.html",goparm,"dialogHeight:600px; dialogWidth:860px; scroll:0; status:0; resizable:1;");
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0070Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//戻り値が空の場合は閉じるで戻った場合。
		return null;
	} else {
		//戻り値３つ。
		retValue=flag + ",";
		for (i = 0;i<3;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}

//**********************************************************
//* 得意先選択ヘルプ
//* 
//* 引数
//* 得意先コード
//* 
//* 返り値
//* ０：仕入先コード
//* １：仕入先名
//* ２：電話番号
//**********************************************************
function PLC1H0080_sel(tokuisakiCd){
	//得意先ヘルプ表示
	var retData = new Array(3);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//実行パラメタ　1:実行urlパス　,2:得意先コード
	goparm= "../../," + tokuisakiCd;
	//入力検索文字チェック
	//ダイアログにより得意先選択ヘルプ表示
	//flag=window.showModalDialog("/logistics/contents/html/PLC1H0080Fram.html",goparm,"dialogHeight:600px; dialogWidth:860px; scroll:0; status:0; resizable:1;");
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0080Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//戻り値が空の場合は閉じるで戻った場合。
		return null;
	} else {
		//戻り値３つ。
		retValue=flag + ",";
		for (i = 0;i<3;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}

//**********************************************************
//* 仕分選択ヘルプ
//* 
//* 引数
//* 仕分コード
//* 管理元会社区分選択フラグ(設定しない場合は選択可能)
//* 	０：管理元会社区分コンボ選択可能
//* 	１：管理元会社区分コンボ選択不可
//* 
//* 返り値
//* ０：仕入先コード
//* １：仕入先名
//* ２：デポコード
//* ３：住所コード
//* ４：管理元会社区分
//**********************************************************
function PLC1H0090_sel(shiwakeCd, flg){
	//仕分ヘルプ表示
	var retData = new Array(5);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//実行パラメタ　1:実行urlパス　,2:仕分コード
	goparm= "../../," + shiwakeCd+ "," + flg;
	//入力検索文字チェック
	//ダイアログにより仕分選択ヘルプ表示
	//flag=window.showModalDialog("/logistics/contents/html/PLC1H0090Fram.html",goparm,"dialogHeight:600px; dialogWidth:860px; scroll:0; status:0; resizable:1;");
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0090Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//戻り値が空の場合は閉じるで戻った場合。
		return null;
	} else {
		//戻り値５つ。
		retValue=flag + ",";
		for (i = 0;i<5;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}

//**********************************************************
//* 売上エリア選択ヘルプ
//* 
//* 引数
//* 売上エリアコード
//* 
//* 返り値
//* ０：売上エリアコード
//* １：売上エリア名
//* ２：売上エリア略称
//**********************************************************
function PLC1H0100_sel(uriageAreaCd){
	//売上エリアヘルプ表示
	var retData = new Array(3);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//実行パラメタ　1:実行urlパス　,2:売上エリアコード
	goparm= "../../," + uriageAreaCd;
	//入力検索文字チェック
	//ダイアログにより売上エリア選択ヘルプ表示
	//flag=window.showModalDialog("/logistics/contents/html/PLC1H0100Fram.html",goparm,"dialogHeight:600px; dialogWidth:860px; scroll:0; status:0; resizable:1;");
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0100Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//戻り値が空の場合は閉じるで戻った場合。
		return null;
	} else {
		//戻り値３つ。
		retValue=flag + ",";
		for (i = 0;i<3;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}

//**********************************************************
//* 送状情報確認
//* 
//* 引数
//* 送状番号
//* 
//* 返り値なし
//**********************************************************
function PLC1H0110_sel(okurijyouNo){
	//送状情報確認表示
	var retData = new Array(3);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//実行パラメタ　1:実行urlパス　,2:送状番号
	goparm= "../../," + okurijyouNo;
	//入力検索文字チェック
	//ダイアログにより送状情報確認表示
	window.showModalDialog("/logistics/contents/html/PLC1H0110Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

}

//**********************************************************
//* 社員選択ヘルプ
//* 
//* 引数
//* 部門コード
//* 管理元会社区分選択フラグ(設定しない場合は選択可能)
//* 	０：管理元会社区分コンボ選択可能
//* 	１：管理元会社区分コンボ選択不可
//* 部門・デポフラグ
//* 	１：部門
//* 	２：デポ
//* 返り値
//* ０：ユーザID
//* １：ユーザ区分
//* ２：社員名
//* ３：部門コード
//* ４：デポコード
//**********************************************************
function PLC1H0120_sel(bumonCd, flg, flg2){
	//受付者選択ヘルプ表示
	var retData = new Array(4);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//実行パラメタ　1:実行urlパス　,2:部門コード,2:管理元会社区分選択フラグ,3:部門・デポフラグ
	goparm= "../../," + bumonCd + "," + flg + "," + flg2;
	//入力検索文字チェック
	//ダイアログにより受付者選択ヘルプ表示
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0120Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//戻り値が空の場合は閉じるで戻った場合。
		return null;
	} else {
		//戻り値５つ。
		retValue=flag + ",";
		for (i = 0;i<5;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}

//**********************************************************
//* お客様選択ヘルプ
//* 
//* 引数
//* お客様名称
//* 
//* 返り値
//* ０：お客様名称
//* １：荷主コード
//* ２：届け先コード
//* ３：郵便番号
//* ４：都道府県コード
//* ５：市区町村コード
//* ６：住所１
//* ７：住所２
//* ８：住所３
//* ９：電話番号
//**********************************************************
function PLC1H0130_sel(userId){
	//お客様選択ヘルプ表示
	var retData = new Array(10);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//実行パラメタ　1:実行urlパス　,2:ユーザID
	goparm= "../../," + userId;
	//入力検索文字チェック
	//ダイアログによりお客様選択ヘルプ表示
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0130Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//戻り値が空の場合は閉じるで戻った場合。
		return null;
	} else {
		//戻り値１０つ。
		retValue=flag + ",";
		for (i = 0;i<10;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}

//**********************************************************
//* 営業所口座入力ヘルプ
//* 
//* 引数
//* デポコード
//* 
//* 返り値
//* ０：会計部門コード
//* １：銀行コード
//* ２：支店コード
//* ３：口座種別
//* ４：口座番号
//* ５：銀行名
//* ６：支店名
//* ７：口座種別名
//**********************************************************
function PLC1H0140_sel(depoCd){
	//営業所口座入力ヘルプ表示
	var retData = new Array(8);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//実行パラメタ　1:実行urlパス　,2:デポコード
	goparm= "../../," + depoCd;
	//入力検索文字チェック
	//ダイアログにより営業所口座入力ヘルプ表示
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0140Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//戻り値が空の場合は閉じるで戻った場合。
		return null;
	} else {
		//戻り値７つ。
		retValue=flag + ",";
		for (i = 0;i<8;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}

//**********************************************************
//* 運賃体系選択ヘルプ
//* 
//* 引数
//* 運賃体系コード
//* 
//* 返り値
//* ０：管理元会社区分
//* １：運賃体系コード
//* ２：運賃体系名称
//**********************************************************
function PLC1H0150_sel(unchintaikeiCd){
	//運賃体系選択ヘルプ表示
	var retData = new Array(10);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//実行パラメタ　1:実行urlパス　,2:運賃体系コード
	goparm= "../../," + unchintaikeiCd;
	//入力検索文字チェック
	//ダイアログにより運賃体系選択ヘルプ表示
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0150Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//戻り値が空の場合は閉じるで戻った場合。
		return null;
	} else {
		//戻り値１０つ。
		retValue=flag + ",";
		for (i = 0;i<10;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}


//**********************************************************
//* 費用対象情報抽出画面
//* 
//* 引数
//* 
//* 返り値
//* 
//**********************************************************
function PLC1B0013_sel(depoCd){
	//費用対象情報抽出画面表示
	var retData = new Array(8);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//実行パラメタ　1:実行urlパス　,2:デポコード
	goparm= "../../," + depoCd;
	//入力検索文字チェック
	//ダイアログにより営業所口座入力ヘルプ表示
	flag=window.showModalDialog("/logistics/contents/html/PLC1B0013Fram.html",document.forms[0],"dialogHeight:250px; dialogWidth:670px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		return null;
	} else {
		return null;
	}
}
//**********************************************************
//* 費用対象情報取込画面
//* 
//* 引数
//* 
//* 返り値
//* 
//**********************************************************
function PLC1B0014_sel(depoCd){
	//費用対象情報抽出画面表示
	var retData = new Array(8);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//実行パラメタ　1:実行urlパス　,2:デポコード
	goparm= "../../," + depoCd;
	//入力検索文字チェック
	//ダイアログにより営業所口座入力ヘルプ表示
	flag=window.showModalDialog("/logistics/contents/html/PLC1B0014Fram.html",goparm,"dialogHeight:500px; dialogWidth:700px; scroll:0; status:0; resizable:1;");
	if (flag==null || flag.length==0){
		return null;
	} else {
		return null;
	}
}
//**********************************************************
//* 担当行政区選択ヘルプ
//* 
//* 引数
//* 担当行政区ＣＤ
//* 
//* 返り値
//* ０：担当行政区CD
//* １：都道府県名
//* ２：市区町村
//**********************************************************
function PLC1H0160_sel(jyusyoCd){
	//住所ヘルプ表示
	var retData = new Array(3);
	var retValue = "";
	var flag;
	var cnt1;
	var goparm;

	//実行パラメタ　1:実行urlパス　,2:担当行政区ＣＤ
	goparm= "../../," + jyusyoCd;
	//入力検索文字チェック
	//ダイアログにより住所選択ヘルプ表示
	flag=window.showModalDialog("/logistics/contents/html/PLC1H0160Fram.html",goparm,"dialogHeight:830px; dialogWidth:860px; scroll:0; status:0; resizable:1;");

	if (flag==null || flag.length==0){
		//戻り値が空の場合は閉じるで戻った場合。
		return null;
	} else {
		//戻り値３つ。
		retValue=flag + ",";
		for (i = 0;i<3;i++ ) {
			cnt1 = retValue.indexOf(",",0);
			retData[i] = retValue.substring(0,cnt1);
			retValue = retValue.substring(cnt1+1,retValue.length);
		}
		return retData;
	}
}