<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class="${properties.kcHtmlClass!}">

<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="robots" content="noindex, nofollow">

    <#if properties.meta?has_content>
        <#list properties.meta?split(' ') as meta>
            <meta name="${meta?split('==')[0]}" content="${meta?split('==')[1]}"/>
        </#list>
    </#if>
        <title>Login</title>
        <link rel="icon" href="${url.resourcesPath}/img/favicon.ico" />
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
    <#if properties.styles?has_content>
        <#list properties.styles?split(' ') as style>
            <link href="${url.resourcesPath}/${style}" rel="stylesheet" />
        </#list>
    </#if>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"</script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
    <#if properties.scripts?has_content>
        <#list properties.scripts?split(' ') as script>
            <script src="${url.resourcesPath}/${script}" type="text/javascript"></script>
        </#list>
    </#if>
    <#if scripts??>
        <#list scripts as script>
            <script src="${script}" type="text/javascript"></script>
        </#list>
    </#if>
</head>

<body>

<#if realm.password>
<div class="container">
    <div class="omb_login">
        <h3 class="omb_authTitle">Login or <a href="${url.registrationUrl}">Sign up</a></h3>
        <div class="row omb_row-sm-offset-3 omb_socialButtons">
            <#if realm.password && social.providers??>
                <#list social.providers as p>
                    <div class="col-xs-4 col-sm-2">
                        <a href="${p.loginUrl}" class="button ${p.providerId}"><span><i class="fa fa-${p.providerId}"></i></span><p>${p.displayName}</p></a>
                    </div>
                </#list>
            </#if>
        </div>

        <div class="row omb_row-sm-offset-3 omb_loginOr">
            <div class="col-xs-12 col-sm-6">
                <hr class="omb_hrOr">
                <span class="omb_spanOr">or</span>
            </div>
        </div>

        <div class="row omb_row-sm-offset-3">
            <div class="col-xs-12 col-sm-6">
                <form class="omb_loginForm" action="${url.loginAction}" autocomplete="off" method="POST">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                        <input type="text" class="form-control" name="username" placeholder="email address">
                    </div>
                    <span class="help-block"></span>

                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                        <input  type="password" class="form-control" name="password" placeholder="Password">
                    </div>

                    <#if message?has_content>
                        <div class="help-block">
                            <div>
                                <#if message.type = 'success'><span class="${properties.kcFeedbackSuccessIcon!}"></span></#if>
                                <#if message.type = 'warning'><span class="${properties.kcFeedbackWarningIcon!}"></span></#if>
                                <#if message.type = 'error'><span class="${properties.kcFeedbackErrorIcon!}"></span></#if>
                                <#if message.type = 'info'><span class="${properties.kcFeedbackInfoIcon!}"></span></#if>
                                <span class="kc-feedback-text">${message.summary}</span>
                            </div>
                        </div>
                    </#if>

                    <div>
                        <#if realm.rememberMe && !usernameEditDisabled??>
                            <div class="rememberme">
                                <label class="checkbox">
                                    <#if login.rememberMe??>
                                        <input id="rememberMe" name="rememberMe" type="checkbox" checked>Remember Me
                                    <#else>
                                        <input id="rememberMe" name="rememberMe" type="checkbox">Remember Me
                                    </#if>
                                </label>
                            </div>
                        </#if>
                    </div>

                    <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
                </form>
                <#if realm.resetPasswordAllowed>
                    <div>
                        <p class="omb_forgotPwd">
                            <a href="${url.loginResetCredentialsUrl}">Forgot password?</a>
                        </p>
                    </div>
                </#if>
            </div>
        </div>
    </div>
</div>
</#if>

</body>