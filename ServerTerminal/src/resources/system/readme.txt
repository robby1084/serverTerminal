//注册到AuthCenter的项目访问URL
WEBSITE=http://115.24.161.21:8082/Desktop/
//项目的入口，用户输入WBSITE中指明的地址之后调转进入的页面地址，可以是action,do,zul,jsp,html......
HOME_GATE=/DesktopAction.action
OPEN_TEST=/test/
//项目中不需要通过用户权限验证就可以访问的页面或者文件夹，之前配置的的参数可以用$参数名代替，例如需要开放OPEN_TEST指明的地址可以用$OPEN_TEST代替，逗号分隔，逗号结束,如下
OPEN_URL=$OPEN_TEST,
//下面可以配置其他的静态访问参数