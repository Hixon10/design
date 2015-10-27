
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._

/**/
object main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.32*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="utf-8" />
        <title>"""),_display_(/*7.17*/title),format.raw/*7.22*/("""</title>
        <script src=""""),_display_(/*8.23*/routes/*8.29*/.Assets.at("javascripts/jquery-1.7.1.min.js")),format.raw/*8.74*/("""" type="text/javascript"></script>
        <script src=""""),_display_(/*9.23*/routes/*9.29*/.Assets.at("javascripts/jquery.mousewheel-min.js")),format.raw/*9.79*/("""" type="text/javascript"></script>
        <script src=""""),_display_(/*10.23*/routes/*10.29*/.Assets.at("javascripts/jquery.terminal-min.js")),format.raw/*10.77*/("""" type="text/javascript"></script>
        <link rel="stylesheet" media="screen" href=""""),_display_(/*11.54*/routes/*11.60*/.Assets.at("stylesheets/jquery.terminal.css")),format.raw/*11.105*/("""">
    </head>
    <body>

        <script>
jQuery(document).ready(function($) """),format.raw/*16.36*/("""{"""),format.raw/*16.37*/("""
    """),format.raw/*17.5*/("""$('body').terminal(function(command, term) """),format.raw/*17.48*/("""{"""),format.raw/*17.49*/("""
        """),format.raw/*18.9*/("""if (command == 'help') """),format.raw/*18.32*/("""{"""),format.raw/*18.33*/("""
            """),format.raw/*19.13*/("""term.echo("available commands are pwd, grep, man, wc, cat");
        """),format.raw/*20.9*/("""}"""),format.raw/*20.10*/(""" """),format.raw/*20.11*/("""else """),format.raw/*20.16*/("""{"""),format.raw/*20.17*/("""
            """),format.raw/*21.13*/("""var encodedCmd = btoa(command);

            var response = $.ajax("""),format.raw/*23.35*/("""{"""),format.raw/*23.36*/("""
                                """),format.raw/*24.33*/("""type: "GET",
                                url: "/shell/" + encodedCmd,
                                async: false
                            """),format.raw/*27.29*/("""}"""),format.raw/*27.30*/(""").responseText;

            response = JSON.parse(response);
            response = response.result;

            if (response.trim()) """),format.raw/*32.34*/("""{"""),format.raw/*32.35*/("""
                """),format.raw/*33.17*/("""term.echo(response);
            """),format.raw/*34.13*/("""}"""),format.raw/*34.14*/(""" """),format.raw/*34.15*/("""else """),format.raw/*34.20*/("""{"""),format.raw/*34.21*/("""
                """),format.raw/*35.17*/("""term.echo("Wrong command");
            """),format.raw/*36.13*/("""}"""),format.raw/*36.14*/("""
        """),format.raw/*37.9*/("""}"""),format.raw/*37.10*/("""
    """),format.raw/*38.5*/("""}"""),format.raw/*38.6*/(""", """),format.raw/*38.8*/("""{"""),format.raw/*38.9*/("""
        """),format.raw/*39.9*/("""greetings: "use help to see available commands",
        onBlur: function() """),format.raw/*40.28*/("""{"""),format.raw/*40.29*/("""
            """),format.raw/*41.13*/("""return false;
        """),format.raw/*42.9*/("""}"""),format.raw/*42.10*/("""
    """),format.raw/*43.5*/("""}"""),format.raw/*43.6*/(""");
"""),format.raw/*44.1*/("""}"""),format.raw/*44.2*/(""");

</script>
    </body>
</html>
"""))}
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue Oct 27 10:36:21 MSK 2015
                  SOURCE: C:/Users/Denis/Desktop/design/web-ui/app/views/main.scala.html
                  HASH: bd1f997f2954a86c37a61ed25ae871f2d8d5865e
                  MATRIX: 727->1|845->31|873->33|1019->153|1044->158|1101->189|1115->195|1180->240|1263->297|1277->303|1347->353|1431->410|1446->416|1515->464|1630->552|1645->558|1712->603|1819->682|1848->683|1880->688|1951->731|1980->732|2016->741|2067->764|2096->765|2137->778|2233->847|2262->848|2291->849|2324->854|2353->855|2394->868|2489->935|2518->936|2579->969|2754->1116|2783->1117|2947->1253|2976->1254|3021->1271|3082->1304|3111->1305|3140->1306|3173->1311|3202->1312|3247->1329|3315->1369|3344->1370|3380->1379|3409->1380|3441->1385|3469->1386|3498->1388|3526->1389|3562->1398|3666->1474|3695->1475|3736->1488|3785->1510|3814->1511|3846->1516|3874->1517|3904->1520|3932->1521
                  LINES: 26->1|29->1|31->3|35->7|35->7|36->8|36->8|36->8|37->9|37->9|37->9|38->10|38->10|38->10|39->11|39->11|39->11|44->16|44->16|45->17|45->17|45->17|46->18|46->18|46->18|47->19|48->20|48->20|48->20|48->20|48->20|49->21|51->23|51->23|52->24|55->27|55->27|60->32|60->32|61->33|62->34|62->34|62->34|62->34|62->34|63->35|64->36|64->36|65->37|65->37|66->38|66->38|66->38|66->38|67->39|68->40|68->40|69->41|70->42|70->42|71->43|71->43|72->44|72->44
                  -- GENERATED --
              */
          