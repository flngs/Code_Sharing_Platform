<!DOCTYPE html>
<html lang="en">
<head>
    <title>Latest</title>
    <meta http-equiv="Content-Type" content="text/html" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <link rel="stylesheet" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<div class="container">
    <#list codes as codes>
<#--    <span class="badge text-bg-info">load date</span>-->
    <span id="load_date" class="text-success">
        ${codes.date}
    </span>
    <pre id="code_snippet"> <#-- class="form-control bg-light" -->
        <code class="form-control bg-light">${codes.code}</code>
    </pre>
    </#list>
</div>
</body>
</html>