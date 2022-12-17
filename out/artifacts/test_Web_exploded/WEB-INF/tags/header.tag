<%@ attribute name="title" required="true" rtexprvalue="true" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <link href="<%=request.getContextPath()%>/resources/core/css/default.css" media="screen" rel="stylesheet" type="text/css" />

    <!-- Title needs to be injected -->
    <title>${title}</title>
</head>
<body>
<div id="maincontainer">
    <div id="topsection">
        <div class="innertube"><h1>This is your flag</h1></div>
    </div>
    <div id="contentwrapper">
        <div id="contentcolumn">

            <!-- Page name needs to be injected -->
            <div><h2>${title}</h2></div>
            <div class="innertube">