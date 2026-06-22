function login()
{
	var url = APP_CONTEXT + "/LoginServlet?type=login&ope_name=";
	url += $("#ope_name").val() + "&ope_pwd=" + $("#ope_pwd").val();
	$.post(url, null, function(rs)
	{
		if (rs == "success")
			window.location.href = APP_CONTEXT + "/pages/index.jsp";
		else
			alert(rs);
	});
}

function logout()
{
	$.post(APP_CONTEXT + "/LoginServlet?type=logout");
	alert("注销成功！");
	window.parent.location.reload();
}

document.onkeydown = function(event)
{
	var e = event || window.event || arguments.callee.caller.arguments[0];
	if (e && e.keyCode == 13)
		login();
};

$(function()
{
	$("#ope_name").focus();
	$("#dou").click(function()
	{
		alert("(⊙o⊙)好神奇啊啊啊啊啊！");//此功能未完善
	});
});