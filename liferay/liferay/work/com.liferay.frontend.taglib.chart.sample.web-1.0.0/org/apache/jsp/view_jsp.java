package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.frontend.taglib.chart.sample.web.constants.ChartSamplePortletKeys;
import com.liferay.frontend.taglib.chart.sample.web.internal.display.context.ChartSampleDisplayContext;

public final class view_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_chart_gauge_id_config_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_chart_donut_id_config_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_chart_area$1spline_id_config_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_chart_predictive_id_config_componentId_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_chart_step_id_config_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_chart_bar_id_config_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_chart_scatter_id_config_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_chart_line_id_config_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_chart_area$1step_id_config_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_chart_spline_id_config_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_chart_line_id_config_componentId_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_chart_combination_id_config_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_chart_pie_id_config_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_chart_geomap_id_config_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_chart_gauge_id_config_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_chart_donut_id_config_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_chart_area$1spline_id_config_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_chart_predictive_id_config_componentId_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_chart_step_id_config_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_chart_bar_id_config_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_chart_scatter_id_config_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_chart_line_id_config_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_chart_area$1step_id_config_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_chart_spline_id_config_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_chart_line_id_config_componentId_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_chart_combination_id_config_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_chart_pie_id_config_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_chart_geomap_id_config_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_chart_gauge_id_config_nobody.release();
    _jspx_tagPool_chart_donut_id_config_nobody.release();
    _jspx_tagPool_aui_script.release();
    _jspx_tagPool_chart_area$1spline_id_config_nobody.release();
    _jspx_tagPool_chart_predictive_id_config_componentId_nobody.release();
    _jspx_tagPool_chart_step_id_config_nobody.release();
    _jspx_tagPool_chart_bar_id_config_nobody.release();
    _jspx_tagPool_chart_scatter_id_config_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_chart_line_id_config_nobody.release();
    _jspx_tagPool_chart_area$1step_id_config_nobody.release();
    _jspx_tagPool_chart_spline_id_config_nobody.release();
    _jspx_tagPool_chart_line_id_config_componentId_nobody.release();
    _jspx_tagPool_chart_combination_id_config_nobody.release();
    _jspx_tagPool_chart_pie_id_config_nobody.release();
    _jspx_tagPool_chart_geomap_id_config_nobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');
      out.write('\n');
      out.write("\n\n\n\n\n\n");
      //  liferay-theme:defineObjects
      com.liferay.taglib.theme.DefineObjectsTag _jspx_th_liferay$1theme_defineObjects_0 = (com.liferay.taglib.theme.DefineObjectsTag) _jspx_tagPool_liferay$1theme_defineObjects_nobody.get(com.liferay.taglib.theme.DefineObjectsTag.class);
      _jspx_th_liferay$1theme_defineObjects_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1theme_defineObjects_0.setParent(null);
      int _jspx_eval_liferay$1theme_defineObjects_0 = _jspx_th_liferay$1theme_defineObjects_0.doStartTag();
      if (_jspx_th_liferay$1theme_defineObjects_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1theme_defineObjects_nobody.reuse(_jspx_th_liferay$1theme_defineObjects_0);
        return;
      }
      _jspx_tagPool_liferay$1theme_defineObjects_nobody.reuse(_jspx_th_liferay$1theme_defineObjects_0);
      com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay = null;
      com.liferay.portal.kernel.model.Company company = null;
      com.liferay.portal.kernel.model.Account account = null;
      com.liferay.portal.kernel.model.User user = null;
      com.liferay.portal.kernel.model.User realUser = null;
      com.liferay.portal.kernel.model.Contact contact = null;
      com.liferay.portal.kernel.model.Layout layout = null;
      java.util.List layouts = null;
      java.lang.Long plid = null;
      com.liferay.portal.kernel.model.LayoutTypePortlet layoutTypePortlet = null;
      java.lang.Long scopeGroupId = null;
      com.liferay.portal.kernel.security.permission.PermissionChecker permissionChecker = null;
      java.util.Locale locale = null;
      java.util.TimeZone timeZone = null;
      com.liferay.portal.kernel.model.Theme theme = null;
      com.liferay.portal.kernel.model.ColorScheme colorScheme = null;
      com.liferay.portal.kernel.theme.PortletDisplay portletDisplay = null;
      java.lang.Long portletGroupId = null;
      themeDisplay = (com.liferay.portal.kernel.theme.ThemeDisplay) _jspx_page_context.findAttribute("themeDisplay");
      company = (com.liferay.portal.kernel.model.Company) _jspx_page_context.findAttribute("company");
      account = (com.liferay.portal.kernel.model.Account) _jspx_page_context.findAttribute("account");
      user = (com.liferay.portal.kernel.model.User) _jspx_page_context.findAttribute("user");
      realUser = (com.liferay.portal.kernel.model.User) _jspx_page_context.findAttribute("realUser");
      contact = (com.liferay.portal.kernel.model.Contact) _jspx_page_context.findAttribute("contact");
      layout = (com.liferay.portal.kernel.model.Layout) _jspx_page_context.findAttribute("layout");
      layouts = (java.util.List) _jspx_page_context.findAttribute("layouts");
      plid = (java.lang.Long) _jspx_page_context.findAttribute("plid");
      layoutTypePortlet = (com.liferay.portal.kernel.model.LayoutTypePortlet) _jspx_page_context.findAttribute("layoutTypePortlet");
      scopeGroupId = (java.lang.Long) _jspx_page_context.findAttribute("scopeGroupId");
      permissionChecker = (com.liferay.portal.kernel.security.permission.PermissionChecker) _jspx_page_context.findAttribute("permissionChecker");
      locale = (java.util.Locale) _jspx_page_context.findAttribute("locale");
      timeZone = (java.util.TimeZone) _jspx_page_context.findAttribute("timeZone");
      theme = (com.liferay.portal.kernel.model.Theme) _jspx_page_context.findAttribute("theme");
      colorScheme = (com.liferay.portal.kernel.model.ColorScheme) _jspx_page_context.findAttribute("colorScheme");
      portletDisplay = (com.liferay.portal.kernel.theme.PortletDisplay) _jspx_page_context.findAttribute("portletDisplay");
      portletGroupId = (java.lang.Long) _jspx_page_context.findAttribute("portletGroupId");
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

ChartSampleDisplayContext chartSampleDisplayContext = (ChartSampleDisplayContext)request.getAttribute(ChartSamplePortletKeys.CHART_SAMPLE_DISPLAY_CONTEXT);

      out.write("\n\n<style type=\"text/css\">\n\t.geomap {\n\t\tmargin: 10px 0 10px 0;\n\t}\n\t.geomap svg {\n\t\twidth: 100%;\n\t\theight: 500px !important;\n\t}\n</style>\n\n<div class=\"container-fluid\">\n\t<div class=\"row\">\n\t\t<div class=\"col\">\n\t\t\t");
      //  chart:area-spline
      com.liferay.frontend.taglib.chart.servlet.taglib.soy.AreaSplineChartTag _jspx_th_chart_area$1spline_0 = (com.liferay.frontend.taglib.chart.servlet.taglib.soy.AreaSplineChartTag) _jspx_tagPool_chart_area$1spline_id_config_nobody.get(com.liferay.frontend.taglib.chart.servlet.taglib.soy.AreaSplineChartTag.class);
      _jspx_th_chart_area$1spline_0.setPageContext(_jspx_page_context);
      _jspx_th_chart_area$1spline_0.setParent(null);
      _jspx_th_chart_area$1spline_0.setConfig( chartSampleDisplayContext.getAreaSplineChartConfig() );
      _jspx_th_chart_area$1spline_0.setId("area-spline");
      int _jspx_eval_chart_area$1spline_0 = _jspx_th_chart_area$1spline_0.doStartTag();
      if (_jspx_th_chart_area$1spline_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_chart_area$1spline_id_config_nobody.reuse(_jspx_th_chart_area$1spline_0);
        return;
      }
      _jspx_tagPool_chart_area$1spline_id_config_nobody.reuse(_jspx_th_chart_area$1spline_0);
      out.write("\n\t\t</div>\n\n\t\t<div class=\"col\">\n\t\t\t");
      //  chart:area-step
      com.liferay.frontend.taglib.chart.servlet.taglib.soy.AreaStepChartTag _jspx_th_chart_area$1step_0 = (com.liferay.frontend.taglib.chart.servlet.taglib.soy.AreaStepChartTag) _jspx_tagPool_chart_area$1step_id_config_nobody.get(com.liferay.frontend.taglib.chart.servlet.taglib.soy.AreaStepChartTag.class);
      _jspx_th_chart_area$1step_0.setPageContext(_jspx_page_context);
      _jspx_th_chart_area$1step_0.setParent(null);
      _jspx_th_chart_area$1step_0.setConfig( chartSampleDisplayContext.getAreaStepChartConfig() );
      _jspx_th_chart_area$1step_0.setId("area-step");
      int _jspx_eval_chart_area$1step_0 = _jspx_th_chart_area$1step_0.doStartTag();
      if (_jspx_th_chart_area$1step_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_chart_area$1step_id_config_nobody.reuse(_jspx_th_chart_area$1step_0);
        return;
      }
      _jspx_tagPool_chart_area$1step_id_config_nobody.reuse(_jspx_th_chart_area$1step_0);
      out.write("\n\t\t</div>\n\t</div>\n</div>\n\n<div class=\"container-fluid\">\n\t<div class=\"row\">\n\t\t<div class=\"col\">\n\t\t\t");
      //  chart:line
      com.liferay.frontend.taglib.chart.servlet.taglib.soy.LineChartTag _jspx_th_chart_line_0 = (com.liferay.frontend.taglib.chart.servlet.taglib.soy.LineChartTag) _jspx_tagPool_chart_line_id_config_nobody.get(com.liferay.frontend.taglib.chart.servlet.taglib.soy.LineChartTag.class);
      _jspx_th_chart_line_0.setPageContext(_jspx_page_context);
      _jspx_th_chart_line_0.setParent(null);
      _jspx_th_chart_line_0.setConfig( chartSampleDisplayContext.getLineChartConfig() );
      _jspx_th_chart_line_0.setId("line");
      int _jspx_eval_chart_line_0 = _jspx_th_chart_line_0.doStartTag();
      if (_jspx_th_chart_line_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_chart_line_id_config_nobody.reuse(_jspx_th_chart_line_0);
        return;
      }
      _jspx_tagPool_chart_line_id_config_nobody.reuse(_jspx_th_chart_line_0);
      out.write("\n\t\t</div>\n\n\t\t<div class=\"col\">\n\t\t\t");
      //  chart:scatter
      com.liferay.frontend.taglib.chart.servlet.taglib.soy.ScatterChartTag _jspx_th_chart_scatter_0 = (com.liferay.frontend.taglib.chart.servlet.taglib.soy.ScatterChartTag) _jspx_tagPool_chart_scatter_id_config_nobody.get(com.liferay.frontend.taglib.chart.servlet.taglib.soy.ScatterChartTag.class);
      _jspx_th_chart_scatter_0.setPageContext(_jspx_page_context);
      _jspx_th_chart_scatter_0.setParent(null);
      _jspx_th_chart_scatter_0.setConfig( chartSampleDisplayContext.getScatterChartConfig() );
      _jspx_th_chart_scatter_0.setId("scatter");
      int _jspx_eval_chart_scatter_0 = _jspx_th_chart_scatter_0.doStartTag();
      if (_jspx_th_chart_scatter_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_chart_scatter_id_config_nobody.reuse(_jspx_th_chart_scatter_0);
        return;
      }
      _jspx_tagPool_chart_scatter_id_config_nobody.reuse(_jspx_th_chart_scatter_0);
      out.write("\n\t\t</div>\n\n\t\t<div class=\"col\">\n\t\t\t");
      //  chart:spline
      com.liferay.frontend.taglib.chart.servlet.taglib.soy.SplineChartTag _jspx_th_chart_spline_0 = (com.liferay.frontend.taglib.chart.servlet.taglib.soy.SplineChartTag) _jspx_tagPool_chart_spline_id_config_nobody.get(com.liferay.frontend.taglib.chart.servlet.taglib.soy.SplineChartTag.class);
      _jspx_th_chart_spline_0.setPageContext(_jspx_page_context);
      _jspx_th_chart_spline_0.setParent(null);
      _jspx_th_chart_spline_0.setConfig( chartSampleDisplayContext.getSplineChartConfig() );
      _jspx_th_chart_spline_0.setId("spline");
      int _jspx_eval_chart_spline_0 = _jspx_th_chart_spline_0.doStartTag();
      if (_jspx_th_chart_spline_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_chart_spline_id_config_nobody.reuse(_jspx_th_chart_spline_0);
        return;
      }
      _jspx_tagPool_chart_spline_id_config_nobody.reuse(_jspx_th_chart_spline_0);
      out.write("\n\t\t</div>\n\n\t\t<div class=\"col\">\n\t\t\t");
      //  chart:step
      com.liferay.frontend.taglib.chart.servlet.taglib.soy.StepChartTag _jspx_th_chart_step_0 = (com.liferay.frontend.taglib.chart.servlet.taglib.soy.StepChartTag) _jspx_tagPool_chart_step_id_config_nobody.get(com.liferay.frontend.taglib.chart.servlet.taglib.soy.StepChartTag.class);
      _jspx_th_chart_step_0.setPageContext(_jspx_page_context);
      _jspx_th_chart_step_0.setParent(null);
      _jspx_th_chart_step_0.setConfig( chartSampleDisplayContext.getStepChartConfig() );
      _jspx_th_chart_step_0.setId("step");
      int _jspx_eval_chart_step_0 = _jspx_th_chart_step_0.doStartTag();
      if (_jspx_th_chart_step_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_chart_step_id_config_nobody.reuse(_jspx_th_chart_step_0);
        return;
      }
      _jspx_tagPool_chart_step_id_config_nobody.reuse(_jspx_th_chart_step_0);
      out.write("\n\t\t</div>\n\t</div>\n</div>\n\n<div class=\"container-fluid\">\n\t<div class=\"row\">\n\t\t<div class=\"col\">\n\t\t\t");
      //  chart:bar
      com.liferay.frontend.taglib.chart.servlet.taglib.soy.BarChartTag _jspx_th_chart_bar_0 = (com.liferay.frontend.taglib.chart.servlet.taglib.soy.BarChartTag) _jspx_tagPool_chart_bar_id_config_nobody.get(com.liferay.frontend.taglib.chart.servlet.taglib.soy.BarChartTag.class);
      _jspx_th_chart_bar_0.setPageContext(_jspx_page_context);
      _jspx_th_chart_bar_0.setParent(null);
      _jspx_th_chart_bar_0.setConfig( chartSampleDisplayContext.getBarChartConfig() );
      _jspx_th_chart_bar_0.setId("bar");
      int _jspx_eval_chart_bar_0 = _jspx_th_chart_bar_0.doStartTag();
      if (_jspx_th_chart_bar_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_chart_bar_id_config_nobody.reuse(_jspx_th_chart_bar_0);
        return;
      }
      _jspx_tagPool_chart_bar_id_config_nobody.reuse(_jspx_th_chart_bar_0);
      out.write("\n\t\t</div>\n\n\t\t<div class=\"col\">\n\t\t\t");
      //  chart:combination
      com.liferay.frontend.taglib.chart.servlet.taglib.soy.CombinationChartTag _jspx_th_chart_combination_0 = (com.liferay.frontend.taglib.chart.servlet.taglib.soy.CombinationChartTag) _jspx_tagPool_chart_combination_id_config_nobody.get(com.liferay.frontend.taglib.chart.servlet.taglib.soy.CombinationChartTag.class);
      _jspx_th_chart_combination_0.setPageContext(_jspx_page_context);
      _jspx_th_chart_combination_0.setParent(null);
      _jspx_th_chart_combination_0.setConfig( chartSampleDisplayContext.getCombinationChartConfig() );
      _jspx_th_chart_combination_0.setId("combination");
      int _jspx_eval_chart_combination_0 = _jspx_th_chart_combination_0.doStartTag();
      if (_jspx_th_chart_combination_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_chart_combination_id_config_nobody.reuse(_jspx_th_chart_combination_0);
        return;
      }
      _jspx_tagPool_chart_combination_id_config_nobody.reuse(_jspx_th_chart_combination_0);
      out.write("\n\t\t</div>\n\t</div>\n</div>\n\n<div class=\"container-fluid\">\n\t<div class=\"row\">\n\t\t<div class=\"col\">\n\t\t\t");
      //  chart:donut
      com.liferay.frontend.taglib.chart.servlet.taglib.soy.DonutChartTag _jspx_th_chart_donut_0 = (com.liferay.frontend.taglib.chart.servlet.taglib.soy.DonutChartTag) _jspx_tagPool_chart_donut_id_config_nobody.get(com.liferay.frontend.taglib.chart.servlet.taglib.soy.DonutChartTag.class);
      _jspx_th_chart_donut_0.setPageContext(_jspx_page_context);
      _jspx_th_chart_donut_0.setParent(null);
      _jspx_th_chart_donut_0.setConfig( chartSampleDisplayContext.getDonutChartConfig() );
      _jspx_th_chart_donut_0.setId("donut");
      int _jspx_eval_chart_donut_0 = _jspx_th_chart_donut_0.doStartTag();
      if (_jspx_th_chart_donut_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_chart_donut_id_config_nobody.reuse(_jspx_th_chart_donut_0);
        return;
      }
      _jspx_tagPool_chart_donut_id_config_nobody.reuse(_jspx_th_chart_donut_0);
      out.write("\n\t\t</div>\n\n\t\t<div class=\"col\">\n\t\t\t");
      //  chart:pie
      com.liferay.frontend.taglib.chart.servlet.taglib.soy.PieChartTag _jspx_th_chart_pie_0 = (com.liferay.frontend.taglib.chart.servlet.taglib.soy.PieChartTag) _jspx_tagPool_chart_pie_id_config_nobody.get(com.liferay.frontend.taglib.chart.servlet.taglib.soy.PieChartTag.class);
      _jspx_th_chart_pie_0.setPageContext(_jspx_page_context);
      _jspx_th_chart_pie_0.setParent(null);
      _jspx_th_chart_pie_0.setConfig( chartSampleDisplayContext.getPieChartConfig() );
      _jspx_th_chart_pie_0.setId("pie");
      int _jspx_eval_chart_pie_0 = _jspx_th_chart_pie_0.doStartTag();
      if (_jspx_th_chart_pie_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_chart_pie_id_config_nobody.reuse(_jspx_th_chart_pie_0);
        return;
      }
      _jspx_tagPool_chart_pie_id_config_nobody.reuse(_jspx_th_chart_pie_0);
      out.write("\n\t\t</div>\n\t</div>\n</div>\n\n<div class=\"container-fluid\">\n\t\t<div class=\"row\">\n\t\t\t<div class=\"col\">\n\t\t\t\t");
      //  chart:gauge
      com.liferay.frontend.taglib.chart.servlet.taglib.soy.GaugeChartTag _jspx_th_chart_gauge_0 = (com.liferay.frontend.taglib.chart.servlet.taglib.soy.GaugeChartTag) _jspx_tagPool_chart_gauge_id_config_nobody.get(com.liferay.frontend.taglib.chart.servlet.taglib.soy.GaugeChartTag.class);
      _jspx_th_chart_gauge_0.setPageContext(_jspx_page_context);
      _jspx_th_chart_gauge_0.setParent(null);
      _jspx_th_chart_gauge_0.setConfig( chartSampleDisplayContext.getGaugeChartConfig() );
      _jspx_th_chart_gauge_0.setId("gauge");
      int _jspx_eval_chart_gauge_0 = _jspx_th_chart_gauge_0.doStartTag();
      if (_jspx_th_chart_gauge_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_chart_gauge_id_config_nobody.reuse(_jspx_th_chart_gauge_0);
        return;
      }
      _jspx_tagPool_chart_gauge_id_config_nobody.reuse(_jspx_th_chart_gauge_0);
      out.write("\n\t\t\t</div>\n\t\t</div>\n\t</div>\n</div>\n\n<div class=\"container-fluid\">\n\t<div class=\"row\">\n\t\t<div class=\"col geomap\">\n\t\t\t");
      //  chart:geomap
      com.liferay.frontend.taglib.chart.servlet.taglib.soy.GeomapTag _jspx_th_chart_geomap_0 = (com.liferay.frontend.taglib.chart.servlet.taglib.soy.GeomapTag) _jspx_tagPool_chart_geomap_id_config_nobody.get(com.liferay.frontend.taglib.chart.servlet.taglib.soy.GeomapTag.class);
      _jspx_th_chart_geomap_0.setPageContext(_jspx_page_context);
      _jspx_th_chart_geomap_0.setParent(null);
      _jspx_th_chart_geomap_0.setConfig( chartSampleDisplayContext.getGeomapConfig1() );
      _jspx_th_chart_geomap_0.setId("geomap-default-colors");
      int _jspx_eval_chart_geomap_0 = _jspx_th_chart_geomap_0.doStartTag();
      if (_jspx_th_chart_geomap_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_chart_geomap_id_config_nobody.reuse(_jspx_th_chart_geomap_0);
        return;
      }
      _jspx_tagPool_chart_geomap_id_config_nobody.reuse(_jspx_th_chart_geomap_0);
      out.write("\n\t\t</div>\n\n\t\t<div class=\"col geomap\">\n\t\t\t");
      //  chart:geomap
      com.liferay.frontend.taglib.chart.servlet.taglib.soy.GeomapTag _jspx_th_chart_geomap_1 = (com.liferay.frontend.taglib.chart.servlet.taglib.soy.GeomapTag) _jspx_tagPool_chart_geomap_id_config_nobody.get(com.liferay.frontend.taglib.chart.servlet.taglib.soy.GeomapTag.class);
      _jspx_th_chart_geomap_1.setPageContext(_jspx_page_context);
      _jspx_th_chart_geomap_1.setParent(null);
      _jspx_th_chart_geomap_1.setConfig( chartSampleDisplayContext.getGeomapConfig2() );
      _jspx_th_chart_geomap_1.setId("gemomap-custom-colors");
      int _jspx_eval_chart_geomap_1 = _jspx_th_chart_geomap_1.doStartTag();
      if (_jspx_th_chart_geomap_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_chart_geomap_id_config_nobody.reuse(_jspx_th_chart_geomap_1);
        return;
      }
      _jspx_tagPool_chart_geomap_id_config_nobody.reuse(_jspx_th_chart_geomap_1);
      out.write("\n\t\t</div>\n\t</div>\n</div>\n\n<div class=\"container-fluid\">\n\t<div class=\"row\">\n\t\t<div class=\"col polling-interval\">\n\t\t\t");
      //  chart:line
      com.liferay.frontend.taglib.chart.servlet.taglib.soy.LineChartTag _jspx_th_chart_line_1 = (com.liferay.frontend.taglib.chart.servlet.taglib.soy.LineChartTag) _jspx_tagPool_chart_line_id_config_componentId_nobody.get(com.liferay.frontend.taglib.chart.servlet.taglib.soy.LineChartTag.class);
      _jspx_th_chart_line_1.setPageContext(_jspx_page_context);
      _jspx_th_chart_line_1.setParent(null);
      _jspx_th_chart_line_1.setComponentId("polling-interval-line-chart");
      _jspx_th_chart_line_1.setConfig( chartSampleDisplayContext.getPollingIntervalLineChartConfig() );
      _jspx_th_chart_line_1.setId("polling-interval-line-chart");
      int _jspx_eval_chart_line_1 = _jspx_th_chart_line_1.doStartTag();
      if (_jspx_th_chart_line_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_chart_line_id_config_componentId_nobody.reuse(_jspx_th_chart_line_1);
        return;
      }
      _jspx_tagPool_chart_line_id_config_componentId_nobody.reuse(_jspx_th_chart_line_1);
      out.write("\n\t\t</div>\n\t</div>\n</div>\n\n<div class=\"container-fluid\">\n\t<div class=\"row\">\n\t\t<div class=\"col predictive\">\n\t\t\t");
      //  chart:predictive
      com.liferay.frontend.taglib.chart.servlet.taglib.soy.PredictiveChartTag _jspx_th_chart_predictive_0 = (com.liferay.frontend.taglib.chart.servlet.taglib.soy.PredictiveChartTag) _jspx_tagPool_chart_predictive_id_config_componentId_nobody.get(com.liferay.frontend.taglib.chart.servlet.taglib.soy.PredictiveChartTag.class);
      _jspx_th_chart_predictive_0.setPageContext(_jspx_page_context);
      _jspx_th_chart_predictive_0.setParent(null);
      _jspx_th_chart_predictive_0.setComponentId("predictive-chart");
      _jspx_th_chart_predictive_0.setConfig( chartSampleDisplayContext.getPredictiveChartConfig() );
      _jspx_th_chart_predictive_0.setId("predictive-chart");
      int _jspx_eval_chart_predictive_0 = _jspx_th_chart_predictive_0.doStartTag();
      if (_jspx_th_chart_predictive_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_chart_predictive_id_config_componentId_nobody.reuse(_jspx_th_chart_predictive_0);
        return;
      }
      _jspx_tagPool_chart_predictive_id_config_componentId_nobody.reuse(_jspx_th_chart_predictive_0);
      out.write("\n\t\t</div>\n\t</div>\n</div>\n\n");
      if (_jspx_meth_aui_script_0(_jspx_page_context))
        return;
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_aui_script_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:script
    com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script.get(com.liferay.taglib.aui.ScriptTag.class);
    _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_script_0.setParent(null);
    int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
    if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_aui_script_0.doInitBody();
      }
      do {
        out.write("\n\tLiferay.componentReady('polling-interval-line-chart').then(\n\t\tfunction(chart) {\n\t\t\tchart.data = function() {\n\t\t\t\treturn Promise.resolve(\n\t\t\t\t\t[\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\tdata: [Math.random() * 100, Math.random() * 100, Math.random() * 100],\n\t\t\t\t\t\t\tid: 'data1'\n\t\t\t\t\t\t},\n\t\t\t\t\t\t{\n\t\t\t\t\t\t\tdata: [Math.random() * 100, Math.random() * 100, Math.random() * 100],\n\t\t\t\t\t\t\tid: 'data2'\n\t\t\t\t\t\t}\n\t\t\t\t\t]\n\t\t\t\t);\n\t\t\t};\n\t\t}\n\t);\n\n\tLiferay.componentReady('predictive-chart').then(\n\t\tfunction(chart) {\n\t\t\tvar oldData = chart.data.slice();\n\n\t\t\tsetTimeout(\n\t\t\t\tfunction() {\n\t\t\t\t\tvar newData = {\n\t\t\t\t\t\tdata: [\n\t\t\t\t\t\t\t[230, 230, 230],\n\t\t\t\t\t\t\t[20, 20, 20],\n\t\t\t\t\t\t\t[120, 120, 120],\n\t\t\t\t\t\t\t[450, 450, 450],\n\t\t\t\t\t\t\t[70, 70, 70],\n\t\t\t\t\t\t\t[280, 280, 280],\n\t\t\t\t\t\t\t[60, 60, 60],\n\t\t\t\t\t\t\t[140, 140, 140],\n\t\t\t\t\t\t\t[220, 245, 305],\n\t\t\t\t\t\t\t[240, 275, 295],\n\t\t\t\t\t\t\t[200, 235, 325],\n\t\t\t\t\t\t\t[110, 145, 235],\n\t\t\t\t\t\t],\n\t\t\t\t\t\tid: 'data3'\n\t\t\t\t\t};\n\n\t\t\t\t\tchart.data = new Promise(\n\t\t\t\t\t\tfunction(resolve, reject) {\n\t\t\t\t\t\t\toldData.push(newData);\n\t\t\t\t\t\t\tresolve(oldData);\n\t\t\t\t\t\t}\n\t\t\t\t\t);\n");
        out.write("\n\t\t\t\t},\n\t\t\t\t4000\n\t\t\t);\n\t\t}\n\t);\n");
        int evalDoAfterBody = _jspx_th_aui_script_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_aui_script_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_0);
      return true;
    }
    _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_0);
    return false;
  }
}
