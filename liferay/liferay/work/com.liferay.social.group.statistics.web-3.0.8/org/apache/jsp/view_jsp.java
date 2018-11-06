package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.settings.ParameterMapSettingsLocator;
import com.liferay.portal.kernel.settings.PortletInstanceSettingsLocator;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.social.group.statistics.web.configuration.SocialGroupStatisticsPortletInstanceConfiguration;
import com.liferay.social.group.statistics.web.internal.constants.SocialGroupStatisticsPortletKeys;
import com.liferay.social.kernel.model.SocialActivityCounter;
import com.liferay.social.kernel.model.SocialActivityCounterConstants;
import com.liferay.social.kernel.service.SocialActivityCounterLocalServiceUtil;
import com.liferay.social.kernel.util.SocialConfigurationUtil;
import com.liferay.social.kernel.util.SocialCounterPeriodUtil;
import com.liferay.social.kernel.util.comparator.SocialActivityCounterNameComparator;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.portlet.PortletURL;

public final class view_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


private static final String[] _BACKGROUND_COLORS = {"#4572A7", "#AA4643", "#89A54E", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"};

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(5);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
    _jspx_dependants.add("/chart/pie.jspf");
    _jspx_dependants.add("/chart/tag_cloud.jspf");
    _jspx_dependants.add("/chart/other.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_row;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_col_width_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_use;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_panel_title_persistState_id_extended_collapsible;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_aui_row = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_col_width_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_use = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_panel_title_persistState_id_extended_collapsible = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_aui_row.release();
    _jspx_tagPool_aui_col_width_cssClass.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_aui_script_use.release();
    _jspx_tagPool_liferay$1ui_panel_title_persistState_id_extended_collapsible.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_c_when_test.release();
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
      out.write("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
      //  liferay-frontend:defineObjects
      com.liferay.frontend.taglib.servlet.taglib.DefineObjectsTag _jspx_th_liferay$1frontend_defineObjects_0 = (com.liferay.frontend.taglib.servlet.taglib.DefineObjectsTag) _jspx_tagPool_liferay$1frontend_defineObjects_nobody.get(com.liferay.frontend.taglib.servlet.taglib.DefineObjectsTag.class);
      _jspx_th_liferay$1frontend_defineObjects_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1frontend_defineObjects_0.setParent(null);
      int _jspx_eval_liferay$1frontend_defineObjects_0 = _jspx_th_liferay$1frontend_defineObjects_0.doStartTag();
      if (_jspx_th_liferay$1frontend_defineObjects_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1frontend_defineObjects_nobody.reuse(_jspx_th_liferay$1frontend_defineObjects_0);
        return;
      }
      _jspx_tagPool_liferay$1frontend_defineObjects_nobody.reuse(_jspx_th_liferay$1frontend_defineObjects_0);
      java.lang.String currentURL = null;
      javax.portlet.PortletURL currentURLObj = null;
      java.util.ResourceBundle resourceBundle = null;
      javax.portlet.WindowState windowState = null;
      currentURL = (java.lang.String) _jspx_page_context.findAttribute("currentURL");
      currentURLObj = (javax.portlet.PortletURL) _jspx_page_context.findAttribute("currentURLObj");
      resourceBundle = (java.util.ResourceBundle) _jspx_page_context.findAttribute("resourceBundle");
      windowState = (javax.portlet.WindowState) _jspx_page_context.findAttribute("windowState");
      out.write('\n');
      out.write('\n');
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
      //  portlet:defineObjects
      com.liferay.taglib.portlet.DefineObjectsTag _jspx_th_portlet_defineObjects_0 = (com.liferay.taglib.portlet.DefineObjectsTag) _jspx_tagPool_portlet_defineObjects_nobody.get(com.liferay.taglib.portlet.DefineObjectsTag.class);
      _jspx_th_portlet_defineObjects_0.setPageContext(_jspx_page_context);
      _jspx_th_portlet_defineObjects_0.setParent(null);
      int _jspx_eval_portlet_defineObjects_0 = _jspx_th_portlet_defineObjects_0.doStartTag();
      if (_jspx_th_portlet_defineObjects_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_portlet_defineObjects_nobody.reuse(_jspx_th_portlet_defineObjects_0);
        return;
      }
      _jspx_tagPool_portlet_defineObjects_nobody.reuse(_jspx_th_portlet_defineObjects_0);
      javax.portlet.ActionRequest actionRequest = null;
      javax.portlet.ActionResponse actionResponse = null;
      javax.portlet.EventRequest eventRequest = null;
      javax.portlet.EventResponse eventResponse = null;
      com.liferay.portal.kernel.portlet.LiferayPortletRequest liferayPortletRequest = null;
      com.liferay.portal.kernel.portlet.LiferayPortletResponse liferayPortletResponse = null;
      javax.portlet.PortletConfig portletConfig = null;
      java.lang.String portletName = null;
      javax.portlet.PortletPreferences portletPreferences = null;
      java.util.Map portletPreferencesValues = null;
      javax.portlet.PortletSession portletSession = null;
      java.util.Map portletSessionScope = null;
      javax.portlet.RenderRequest renderRequest = null;
      javax.portlet.RenderResponse renderResponse = null;
      javax.portlet.ResourceRequest resourceRequest = null;
      javax.portlet.ResourceResponse resourceResponse = null;
      actionRequest = (javax.portlet.ActionRequest) _jspx_page_context.findAttribute("actionRequest");
      actionResponse = (javax.portlet.ActionResponse) _jspx_page_context.findAttribute("actionResponse");
      eventRequest = (javax.portlet.EventRequest) _jspx_page_context.findAttribute("eventRequest");
      eventResponse = (javax.portlet.EventResponse) _jspx_page_context.findAttribute("eventResponse");
      liferayPortletRequest = (com.liferay.portal.kernel.portlet.LiferayPortletRequest) _jspx_page_context.findAttribute("liferayPortletRequest");
      liferayPortletResponse = (com.liferay.portal.kernel.portlet.LiferayPortletResponse) _jspx_page_context.findAttribute("liferayPortletResponse");
      portletConfig = (javax.portlet.PortletConfig) _jspx_page_context.findAttribute("portletConfig");
      portletName = (java.lang.String) _jspx_page_context.findAttribute("portletName");
      portletPreferences = (javax.portlet.PortletPreferences) _jspx_page_context.findAttribute("portletPreferences");
      portletPreferencesValues = (java.util.Map) _jspx_page_context.findAttribute("portletPreferencesValues");
      portletSession = (javax.portlet.PortletSession) _jspx_page_context.findAttribute("portletSession");
      portletSessionScope = (java.util.Map) _jspx_page_context.findAttribute("portletSessionScope");
      renderRequest = (javax.portlet.RenderRequest) _jspx_page_context.findAttribute("renderRequest");
      renderResponse = (javax.portlet.RenderResponse) _jspx_page_context.findAttribute("renderResponse");
      resourceRequest = (javax.portlet.ResourceRequest) _jspx_page_context.findAttribute("resourceRequest");
      resourceResponse = (javax.portlet.ResourceResponse) _jspx_page_context.findAttribute("resourceResponse");
      out.write('\n');
      out.write('\n');

SocialGroupStatisticsPortletInstanceConfiguration socialGroupStatisticsPortletInstanceConfiguration = ConfigurationProviderUtil.getConfiguration(SocialGroupStatisticsPortletInstanceConfiguration.class, new PortletInstanceSettingsLocator(themeDisplay.getLayout(), portletDisplay.getId()));

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

String[] displayActivityCounterNames = socialGroupStatisticsPortletInstanceConfiguration.displayActivityCounterName();

for (int displayActivityCounterNameIndex = 0; displayActivityCounterNameIndex < displayActivityCounterNames.length; displayActivityCounterNameIndex++) {
	String displayActivityCounterName = displayActivityCounterNames[displayActivityCounterNameIndex];

	if (Validator.isNull(displayActivityCounterName)) {
		continue;
	}

	String[] chartTypes = socialGroupStatisticsPortletInstanceConfiguration.chartType();

	String chartType = GetterUtil.getString(chartTypes[displayActivityCounterNameIndex], "area");

	String[] chartWidths = socialGroupStatisticsPortletInstanceConfiguration.chartWidth();

	int chartWidth = GetterUtil.getInteger(chartWidths[displayActivityCounterNameIndex], 35);

	String[] dataRanges = socialGroupStatisticsPortletInstanceConfiguration.dataRange();

	String dataRange = GetterUtil.getString(dataRanges[displayActivityCounterNameIndex], "year");

	List<AssetTag> assetTags = null;

	List<SocialActivityCounter> activityCounters = null;

	String title = LanguageUtil.get(request, "site-statistics") + StringPool.SPACE;

	int dataSize = 0;
	int displayHeight = 80;

	if (chartType.equals("tag-cloud")) {
		if (dataRange.equals("year")) {
			assetTags = AssetTagLocalServiceUtil.getSocialActivityCounterPeriodTags(scopeGroupId, displayActivityCounterName, SocialCounterPeriodUtil.getFirstActivityDayOfYear(), SocialCounterPeriodUtil.getEndPeriod());
		}
		else {
			assetTags = AssetTagLocalServiceUtil.getSocialActivityCounterOffsetTags(scopeGroupId, displayActivityCounterName, -12, 0);
		}

		title = title + LanguageUtil.format(request, "tag-cloud-for-x", LanguageUtil.get(request, "group.statistics.title." + displayActivityCounterName), false);

		dataSize = assetTags.size();
	}
	else {
		if (chartType.equals("pie")) {
			if (dataRange.equals("year")) {
				activityCounters = SocialActivityCounterLocalServiceUtil.getPeriodDistributionActivityCounters(scopeGroupId, displayActivityCounterName, SocialCounterPeriodUtil.getFirstActivityDayOfYear(), SocialCounterPeriodUtil.getEndPeriod());
			}
			else {
				activityCounters = SocialActivityCounterLocalServiceUtil.getOffsetDistributionActivityCounters(scopeGroupId, displayActivityCounterName, -12, 0);
			}

			displayHeight = Math.max((activityCounters.size() + 1) * 18, displayHeight);
		}
		else {
			if (dataRange.equals("year")) {
				activityCounters = SocialActivityCounterLocalServiceUtil.getPeriodActivityCounters(scopeGroupId, displayActivityCounterName, SocialCounterPeriodUtil.getFirstActivityDayOfYear(), SocialCounterPeriodUtil.getEndPeriod());
			}
			else {
				activityCounters = SocialActivityCounterLocalServiceUtil.getOffsetActivityCounters(scopeGroupId, displayActivityCounterName, -12, 0);
			}
		}

		dataSize = activityCounters.size();

		title = title + LanguageUtil.get(request, "group.statistics.title." + displayActivityCounterName);
	}

	if (dataSize == 0) {
		displayHeight = 40;
	}

      out.write("\n\n\t<div class=\"group-statistics-container\">\n\t\t");
      //  liferay-ui:panel
      com.liferay.taglib.ui.PanelTag _jspx_th_liferay$1ui_panel_0 = (com.liferay.taglib.ui.PanelTag) _jspx_tagPool_liferay$1ui_panel_title_persistState_id_extended_collapsible.get(com.liferay.taglib.ui.PanelTag.class);
      _jspx_th_liferay$1ui_panel_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1ui_panel_0.setParent(null);
      _jspx_th_liferay$1ui_panel_0.setCollapsible( true );
      _jspx_th_liferay$1ui_panel_0.setExtended( true );
      _jspx_th_liferay$1ui_panel_0.setId( "groupStatisticsPanel" + displayActivityCounterNameIndex );
      _jspx_th_liferay$1ui_panel_0.setPersistState( true );
      _jspx_th_liferay$1ui_panel_0.setTitle( title );
      int _jspx_eval_liferay$1ui_panel_0 = _jspx_th_liferay$1ui_panel_0.doStartTag();
      if (_jspx_eval_liferay$1ui_panel_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t\t<div class=\"chart-");
        out.print( HtmlUtil.escapeAttribute(chartType) );
        out.write(" group-statistics-body\" style=\"min-height: ");
        out.print( displayHeight );
        out.write("px;\">\n\t\t\t\t");
        //  c:choose
        com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
        _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
        _jspx_th_c_choose_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_0);
        int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
        if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t\t\t");
          //  c:when
          com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
          _jspx_th_c_when_0.setPageContext(_jspx_page_context);
          _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
          _jspx_th_c_when_0.setTest( dataSize > 0 );
          int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
          if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t\t");
            //  c:choose
            com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
            _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
            _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
            int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
            if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t\t\t");
              //  c:when
              com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
              _jspx_th_c_when_1.setPageContext(_jspx_page_context);
              _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
              _jspx_th_c_when_1.setTest( chartType.equals("pie") );
              int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
              if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t\t\t");
                out.write('\n');
                out.write('\n');

int[] categories = new int[activityCounters.size()];
int[] values = new int[activityCounters.size()];

int totalValue = 0;

for (int i = 0; i < activityCounters.size(); i++) {
	SocialActivityCounter activityCounter = activityCounters.get(i);

	categories[i] = i + 1;
	values[i] = activityCounter.getCurrentValue();

	totalValue = totalValue + activityCounter.getCurrentValue();
}

DecimalFormat decimalFormat = new DecimalFormat("##.00%");

                out.write('\n');
                out.write('\n');
                //  aui:row
                com.liferay.taglib.aui.RowTag _jspx_th_aui_row_0 = (com.liferay.taglib.aui.RowTag) _jspx_tagPool_aui_row.get(com.liferay.taglib.aui.RowTag.class);
                _jspx_th_aui_row_0.setPageContext(_jspx_page_context);
                _jspx_th_aui_row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                int _jspx_eval_aui_row_0 = _jspx_th_aui_row_0.doStartTag();
                if (_jspx_eval_aui_row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write('\n');
                  out.write('	');
                  //  aui:col
                  com.liferay.taglib.aui.ColTag _jspx_th_aui_col_0 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width_cssClass.get(com.liferay.taglib.aui.ColTag.class);
                  _jspx_th_aui_col_0.setPageContext(_jspx_page_context);
                  _jspx_th_aui_col_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_0);
                  _jspx_th_aui_col_0.setCssClass("chart-column");
                  _jspx_th_aui_col_0.setWidth( chartWidth );
                  int _jspx_eval_aui_col_0 = _jspx_th_aui_col_0.doStartTag();
                  if (_jspx_eval_aui_col_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t<div class=\"group-statistics-chart\">\n\t\t\t<div id=\"groupStatisticsChart");
                    out.print( displayActivityCounterNameIndex );
                    out.write("\"></div>\n\t\t</div>\n\t");
                  }
                  if (_jspx_th_aui_col_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_0);
                    return;
                  }
                  _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_0);
                  out.write("\n\n\t");
                  //  aui:col
                  com.liferay.taglib.aui.ColTag _jspx_th_aui_col_1 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width_cssClass.get(com.liferay.taglib.aui.ColTag.class);
                  _jspx_th_aui_col_1.setPageContext(_jspx_page_context);
                  _jspx_th_aui_col_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_0);
                  _jspx_th_aui_col_1.setCssClass("info-column");
                  _jspx_th_aui_col_1.setWidth( 100 - chartWidth );
                  int _jspx_eval_aui_col_1 = _jspx_th_aui_col_1.doStartTag();
                  if (_jspx_eval_aui_col_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t<div class=\"group-statistics-info\">\n\t\t\t<strong>");
                    if (_jspx_meth_liferay$1ui_message_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_col_1, _jspx_page_context))
                      return;
                    out.write(":</strong>\n\n\t\t\t<table>\n\n\t\t\t\t");

				for (int i = 0; i < activityCounters.size(); i++) {
					SocialActivityCounter activityCounter = activityCounters.get(i);
				
                    out.write("\n\n\t\t\t\t\t<tr>\n\t\t\t\t\t\t<td>\n\t\t\t\t\t\t\t<div class=\"group-statistics-color-marker\" style=\"background-color: ");
                    out.print( _BACKGROUND_COLORS[i % _BACKGROUND_COLORS.length] );
                    out.write("\"></div>\n\t\t\t\t\t\t</td>\n\t\t\t\t\t\t<td>\n\t\t\t\t\t\t\t");
                    out.print( ResourceActionsUtil.getModelResource(locale, activityCounter.getClassName()) );
                    out.write("\n\t\t\t\t\t\t</td>\n\t\t\t\t\t\t<td>\n\t\t\t\t\t\t\t:\n\t\t\t\t\t\t</td>\n\t\t\t\t\t\t<td align=\"right\">\n\n\t\t\t\t\t\t\t");

							double percentage = 0;

							if (totalValue > 0) {
								percentage = (double)activityCounter.getCurrentValue() / (double)totalValue;
							}
							
                    out.write("\n\n\t\t\t\t\t\t\t");
                    out.print( decimalFormat.format(percentage) );
                    out.write("\n\t\t\t\t\t\t</td>\n\t\t\t\t\t</tr>\n\n\t\t\t\t");

				}
				
                    out.write("\n\n\t\t\t</table>\n\t\t</div>\n\t");
                  }
                  if (_jspx_th_aui_col_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_1);
                    return;
                  }
                  _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_1);
                  out.write('\n');
                }
                if (_jspx_th_aui_row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_0);
                  return;
                }
                _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_0);
                out.write('\n');
                out.write('\n');
                //  aui:script
                com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
                _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
                _jspx_th_aui_script_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                _jspx_th_aui_script_0.setUse("charts");
                int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
                if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_aui_script_0.doInitBody();
                  }
                  do {
                    out.write("\n\tvar categories = [");
                    out.print( StringUtil.merge(categories) );
                    out.write("];\n\tvar values = [");
                    out.print( StringUtil.merge(values) );
                    out.write("];\n\n\tvar data = [];\n\n\tfor (var i = 0; i < categories.length; i++) {\n\t\tdata.push(\n\t\t\t{\n\t\t\t\tcategory: categories[i],\n\t\t\t\tvalues: values[i]\n\t\t\t}\n\t\t);\n\t}\n\n\tvar tooltip = {\n\t\tmarkerLabelFunction: function(categoryItem, valueItem, itemIndex, series, seriesIndex) {\n\t\t\treturn valueItem.value;\n\t\t},\n\n\t\tstyles: {\n\t\t\tbackgroundColor: '#FFF',\n\t\t\tborderColor: '#4572A7',\n\t\t\tborderWidth: 1,\n\t\t\tcolor: '#000',\n\t\t\ttextAlign: 'center',\n\t\t\twidth: 30\n\t\t}\n\t};\n\n\tvar chartContainer = A.one('#groupStatisticsChart");
                    out.print( displayActivityCounterNameIndex );
                    out.write("');\n\n\tvar getContainerWidth = function(container) {\n\t\treturn container.width() * 0.7;\n\t};\n\n\tvar containerWidth = getContainerWidth(chartContainer);\n\n\tchartContainer.setStyle('height', containerWidth);\n\n\tvar chart = new A.Chart(\n\t\t{\n\t\t\tdataProvider: data,\n\t\t\theight: containerWidth,\n\t\t\tseriesCollection: [\n\t\t\t\t{\n\t\t\t\t\tcategoryKey: 'category',\n\t\t\t\t\tstyles: {\n\t\t\t\t\t\tborder: {\n\t\t\t\t\t\t\talpha: 0.8,\n\t\t\t\t\t\t\tcolors: new Array(9).join('#333,').split(','),\n\t\t\t\t\t\t\tweight: 1\n\t\t\t\t\t\t},\n\t\t\t\t\t\tfill: {\n\t\t\t\t\t\t\tcolors: ['");
                    out.print( StringUtil.merge(_BACKGROUND_COLORS, "', '") );
                    out.write("']\n\t\t\t\t\t\t}\n\t\t\t\t\t},\n\t\t\t\t\tvalueKey: 'values'\n\t\t\t\t}\n\t\t\t],\n\t\t\ttooltip: tooltip,\n\t\t\ttype: 'pie',\n\t\t\twidth: containerWidth\n\t\t}\n\t).render(chartContainer);\n\n\tLiferay.after(\n\t\t['portletMoved', 'liferaypanel:collapse'],\n\t\tfunction(event) {\n\t\t\tvar width = getContainerWidth(chartContainer);\n\n\t\t\tif (width && (event.type == 'portletMoved' && event.portletId == '");
                    out.print( portletDisplay.getId() );
                    out.write("') ||\n\t\t\t\t(event.type == 'liferaypanel:collapse' && event.panelId == 'groupStatisticsPanel");
                    out.print( displayActivityCounterNameIndex );
                    out.write("')) {\n\n\t\t\t\tchart.set('width', width);\n\n\t\t\t\tchartContainer.setStyle('height', width);\n\t\t\t}\n\t\t}\n\t);\n");
                    int evalDoAfterBody = _jspx_th_aui_script_0.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_aui_script_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_0);
                  return;
                }
                _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_0);
                out.write('\n');
                out.write('\n');
                out.write("\n\t\t\t\t\t\t\t");
              }
              if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                return;
              }
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
              out.write("\n\t\t\t\t\t\t\t");
              //  c:when
              com.liferay.taglib.core.WhenTag _jspx_th_c_when_2 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
              _jspx_th_c_when_2.setPageContext(_jspx_page_context);
              _jspx_th_c_when_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
              _jspx_th_c_when_2.setTest( chartType.equals("tag-cloud") );
              int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
              if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t\t\t");
                out.write('\n');
                out.write('\n');

String assetTagName = ParamUtil.getString(request, "tag");

int maxValue = 0;
int minValue = Integer.MAX_VALUE;

for (AssetTag assetTag : assetTags) {
	maxValue = Math.max(maxValue, assetTag.getAssetCount());
	minValue = Math.min(minValue, assetTag.getAssetCount());
}

double multiplier = 1;

if (maxValue != minValue) {
	multiplier = (double)5 / (maxValue - minValue);
}

PortletURL portletURL = renderResponse.createRenderURL();

                out.write("\n\n<ul class=\"tag-cloud tag-items\">\n\n\t");

	for (AssetTag assetTag : assetTags) {
		int popularity = (int)(1 + ((maxValue - (maxValue - (assetTag.getAssetCount() - minValue))) * multiplier));
	
                out.write("\n\n\t\t<li class=\"tag-popularity-");
                out.print( popularity );
                out.write("\">\n\t\t\t<span>\n\n\t\t\t\t");

				if (assetTagName.equals(assetTag.getName())) {
					portletURL.setParameter("tag", StringPool.BLANK);
				
                out.write("\n\n\t\t\t\t\t<a class=\"tag-selected\" href=\"");
                out.print( portletURL );
                out.write("\">\n\n\t\t\t\t");

				}
				else {
					portletURL.setParameter("tag", assetTag.getName());
				
                out.write("\n\n\t\t\t\t\t<a href=\"");
                out.print( portletURL );
                out.write("\">\n\n\t\t\t\t");

				}
				
                out.write("\n\n\t\t\t\t\t<strong>");
                out.print( HtmlUtil.escape(assetTag.getName()) );
                out.write("</strong>\n\t\t\t\t</a>\n\t\t\t</span>\n\t\t</li>\n\n\t");

	}
	
                out.write("\n\n</ul>");
                out.write("\n\t\t\t\t\t\t\t");
              }
              if (_jspx_th_c_when_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                return;
              }
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
              out.write("\n\t\t\t\t\t\t\t");
              //  c:otherwise
              com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
              _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
              _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
              int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
              if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t\t\t\t");
                out.write('\n');
                out.write('\n');

SocialActivityCounter highestActivityCounter = null;
SocialActivityCounter lowestActivityCounter = null;

int[] categories = new int[activityCounters.size()];
int[] values = new int[activityCounters.size()];

int currentValue = 0;
int totalValue = 0;

int totalDays = 0;

for (int i = 0; i < activityCounters.size(); i++) {
	SocialActivityCounter activityCounter = activityCounters.get(i);

	if ((highestActivityCounter == null) || (highestActivityCounter.getCurrentValue() < activityCounter.getCurrentValue())) {
		highestActivityCounter = activityCounter;
	}

	if ((lowestActivityCounter == null) || (lowestActivityCounter.getCurrentValue() > activityCounter.getCurrentValue())) {
		lowestActivityCounter = activityCounter;
	}

	categories[i] = i + 1;
	values[i] = activityCounter.getCurrentValue();

	totalValue = totalValue + activityCounter.getCurrentValue();

	if (activityCounter.getEndPeriod() == -1) {
		currentValue = activityCounter.getCurrentValue();

		totalDays = totalDays + SocialCounterPeriodUtil.getActivityDay() - activityCounter.getStartPeriod() + 1;
	}
	else {
		totalDays = totalDays + activityCounter.getEndPeriod() - activityCounter.getStartPeriod() + 1;
	}
}

Format format = FastDateFormatFactoryUtil.getSimpleDateFormat("MMM d");

                out.write('\n');
                out.write('\n');
                //  aui:row
                com.liferay.taglib.aui.RowTag _jspx_th_aui_row_1 = (com.liferay.taglib.aui.RowTag) _jspx_tagPool_aui_row.get(com.liferay.taglib.aui.RowTag.class);
                _jspx_th_aui_row_1.setPageContext(_jspx_page_context);
                _jspx_th_aui_row_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
                int _jspx_eval_aui_row_1 = _jspx_th_aui_row_1.doStartTag();
                if (_jspx_eval_aui_row_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write('\n');
                  out.write('	');
                  //  aui:col
                  com.liferay.taglib.aui.ColTag _jspx_th_aui_col_2 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width_cssClass.get(com.liferay.taglib.aui.ColTag.class);
                  _jspx_th_aui_col_2.setPageContext(_jspx_page_context);
                  _jspx_th_aui_col_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_1);
                  _jspx_th_aui_col_2.setCssClass("chart-column");
                  _jspx_th_aui_col_2.setWidth( chartWidth );
                  int _jspx_eval_aui_col_2 = _jspx_th_aui_col_2.doStartTag();
                  if (_jspx_eval_aui_col_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t<div class=\"group-statistics-chart\" id=\"groupStatisticsChart");
                    out.print( displayActivityCounterNameIndex );
                    out.write("\" style=\"height: ");
                    out.print( displayHeight - 2 );
                    out.write("px;\"></div>\n\t");
                  }
                  if (_jspx_th_aui_col_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_2);
                    return;
                  }
                  _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_2);
                  out.write("\n\n\t");
                  //  aui:col
                  com.liferay.taglib.aui.ColTag _jspx_th_aui_col_3 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width_cssClass.get(com.liferay.taglib.aui.ColTag.class);
                  _jspx_th_aui_col_3.setPageContext(_jspx_page_context);
                  _jspx_th_aui_col_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_1);
                  _jspx_th_aui_col_3.setCssClass("info-column");
                  _jspx_th_aui_col_3.setWidth( 100 - chartWidth );
                  int _jspx_eval_aui_col_3 = _jspx_th_aui_col_3.doStartTag();
                  if (_jspx_eval_aui_col_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t<div class=\"group-statistics-info\">\n\t\t\t");
                    if (_jspx_meth_liferay$1ui_message_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_col_3, _jspx_page_context))
                      return;
                    out.write(':');
                    out.write(' ');
                    out.print( currentValue );
                    out.write("<br />\n\n\t\t\t");
                    if (_jspx_meth_liferay$1ui_message_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_col_3, _jspx_page_context))
                      return;
                    out.write(':');
                    out.write(' ');
                    out.print( Math.round(totalValue / totalDays * 100) / 100 );
                    out.write("<br />\n\n\t\t\t");
                    if (_jspx_meth_liferay$1ui_message_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_col_3, _jspx_page_context))
                      return;
                    out.write(": <span class=\"group-statistics-activity-period\">\n\t\t\t\t<strong>\n\t\t\t\t\t");
                    out.print( format.format(SocialCounterPeriodUtil.getDate(highestActivityCounter.getStartPeriod())) );
                    out.write("\n\t\t\t\t\t\t-\n\t\t\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_3);
                    _jspx_th_c_if_0.setTest( highestActivityCounter.getEndPeriod() != -1 );
                    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
                    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t");
                      out.print( format.format(SocialCounterPeriodUtil.getDate(highestActivityCounter.getEndPeriod())) );
                      out.write("\n\t\t\t\t\t");
                    }
                    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
                    out.write("\n\n\t\t\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_1.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_3);
                    _jspx_th_c_if_1.setTest( highestActivityCounter.getEndPeriod() == -1 );
                    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
                    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t");
                      out.print( format.format(new Date()) );
                      out.write("\n\t\t\t\t\t");
                    }
                    if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                    out.write("\n\t\t\t\t</strong>\n\t\t\t</span>\n\n\t\t\t(");
                    out.print( highestActivityCounter.getCurrentValue() );
                    out.write(")<br />\n\n\t\t\t");
                    if (_jspx_meth_liferay$1ui_message_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_col_3, _jspx_page_context))
                      return;
                    out.write(": <span class=\"group-statistics-activity-period\">\n\t\t\t\t<strong>\n\t\t\t\t\t");
                    out.print( format.format(SocialCounterPeriodUtil.getDate(lowestActivityCounter.getStartPeriod())) );
                    out.write("\n\t\t\t\t\t\t-\n\t\t\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_2.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_3);
                    _jspx_th_c_if_2.setTest( lowestActivityCounter.getEndPeriod() != -1 );
                    int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
                    if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t");
                      out.print( format.format(SocialCounterPeriodUtil.getDate(lowestActivityCounter.getEndPeriod())) );
                      out.write("\n\t\t\t\t\t");
                    }
                    if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                    out.write("\n\n\t\t\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_3.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_3);
                    _jspx_th_c_if_3.setTest( lowestActivityCounter.getEndPeriod() == -1 );
                    int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
                    if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t");
                      out.print( format.format(new Date()) );
                      out.write("\n\t\t\t\t\t");
                    }
                    if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                    out.write("\n\t\t\t\t</strong>\n\t\t\t</span>\n\n\t\t\t(");
                    out.print( lowestActivityCounter.getCurrentValue() );
                    out.write(")<br />\n\t\t</div>\n\t");
                  }
                  if (_jspx_th_aui_col_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_3);
                    return;
                  }
                  _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_3);
                  out.write('\n');
                }
                if (_jspx_th_aui_row_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_1);
                  return;
                }
                _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_1);
                out.write('\n');
                out.write('\n');
                //  aui:script
                com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_1 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
                _jspx_th_aui_script_1.setPageContext(_jspx_page_context);
                _jspx_th_aui_script_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
                _jspx_th_aui_script_1.setUse("charts");
                int _jspx_eval_aui_script_1 = _jspx_th_aui_script_1.doStartTag();
                if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_aui_script_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_aui_script_1.doInitBody();
                  }
                  do {
                    out.write("\n\tvar categories = [");
                    out.print( StringUtil.merge(categories) );
                    out.write("];\n\tvar values = [");
                    out.print( StringUtil.merge(values) );
                    out.write("];\n\n\tvar data = [];\n\n\tfor (var i = 0; i < categories.length; i++) {\n\t\tdata.push(\n\t\t\t{\n\t\t\t\tcategory: categories[i],\n\t\t\t\tvalues: values[i]\n\t\t\t}\n\t\t);\n\t}\n\n\tvar tooltip = {\n\t\tmarkerLabelFunction: function(categoryItem, valueItem, itemIndex, series, seriesIndex) {\n\t\t\treturn valueItem.value;\n\t\t},\n\n\t\tstyles: {\n\t\t\tbackgroundColor: '#FFF',\n\t\t\tborderColor: '#4572A7',\n\t\t\tborderWidth: 1,\n\t\t\tcolor: '#000',\n\t\t\ttextAlign: 'center',\n\t\t\twidth: 30\n\t\t}\n\t};\n\n\tvar chartType;\n\tvar customConfig;\n\n\t");
                    //  c:choose
                    com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_2 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                    _jspx_th_c_choose_2.setPageContext(_jspx_page_context);
                    _jspx_th_c_choose_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
                    int _jspx_eval_c_choose_2 = _jspx_th_c_choose_2.doStartTag();
                    if (_jspx_eval_c_choose_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_3 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_3.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                      _jspx_th_c_when_3.setTest( chartType.equals("area") );
                      int _jspx_eval_c_when_3 = _jspx_th_c_when_3.doStartTag();
                      if (_jspx_eval_c_when_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\tchartType = 'combo';\n\n\t\t\tcustomConfig = {\n\t\t\t\tshowAreaFill: true,\n\t\t\t\tshowMarkers: true,\n\t\t\t\tstyles: {\n\t\t\t\t\tseries: {\n\t\t\t\t\t\tvalues: {\n\t\t\t\t\t\t\tarea: {\n\t\t\t\t\t\t\t\talpha: 0.4,\n\t\t\t\t\t\t\t\tcolor: '#5CC0FF'\n\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\tline: {\n\t\t\t\t\t\t\t\tcolor: '#4572A7',\n\t\t\t\t\t\t\t\tweight: 2\n\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\tmarker: {\n\t\t\t\t\t\t\t\tfill: {\n\t\t\t\t\t\t\t\t\tcolor: '#3CCFFF'\n\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\theight: 6\n\t\t\t\t\t\t\t}\n\t\t\t\t\t\t}\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t};\n\t\t");
                      }
                      if (_jspx_th_c_when_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
                      out.write("\n\t\t");
                      //  c:otherwise
                      com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_1 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                      _jspx_th_c_otherwise_1.setPageContext(_jspx_page_context);
                      _jspx_th_c_otherwise_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                      int _jspx_eval_c_otherwise_1 = _jspx_th_c_otherwise_1.doStartTag();
                      if (_jspx_eval_c_otherwise_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\tchartType = '");
                        out.print( HtmlUtil.escapeJS(chartType) );
                        out.write("';\n\n\t\t\tcustomConfig = {\n\t\t\t\tshowAreaFill: true,\n\t\t\t\tshowMarkers: true\n\t\t\t};\n\t\t");
                      }
                      if (_jspx_th_c_otherwise_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                        return;
                      }
                      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                      out.write('\n');
                      out.write('	');
                    }
                    if (_jspx_th_c_choose_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
                      return;
                    }
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
                    out.write("\n\n\tvar chartContainer = A.one('#groupStatisticsChart");
                    out.print( displayActivityCounterNameIndex );
                    out.write("');\n\n\tvar defaultConfig = {\n\t\taxes: {\n\t\t\tcategory: {\n\t\t\t\tstyles:\n\t\t\t\t{\n\t\t\t\t\tlabel: {\n\t\t\t\t\t\tdisplay: 'none'\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t},\n\t\t\tvalues: {\n\t\t\t\tstyles: {\n\t\t\t\t\tmajorUnit: {\n\t\t\t\t\t\tcount: 6\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t}\n\t\t},\n\t\tdataProvider: data,\n\t\theight: ");
                    out.print( displayHeight - 2 );
                    out.write(",\n\t\thorizontalGridlines: true,\n\t\ttooltip: tooltip,\n\t\ttype: chartType,\n\t\twidth: chartContainer.width()\n\t};\n\n\tA.mix(defaultConfig, customConfig);\n\n\tvar chart = new A.Chart(defaultConfig).render(chartContainer);\n\n\tLiferay.after(\n\t\t['portletMoved', 'liferaypanel:collapse'],\n\t\tfunction(event) {\n\t\t\tvar width = chartContainer.width();\n\n\t\t\tif (width && (event.type == 'portletMoved' && event.portletId == '");
                    out.print( portletDisplay.getId() );
                    out.write("') ||\n\t\t\t\t(event.type == 'liferaypanel:collapse' && event.panelId == 'groupStatisticsPanel");
                    out.print( displayActivityCounterNameIndex );
                    out.write("')) {\n\n\t\t\t\tchart.set('width', width);\n\t\t\t}\n\t\t}\n\t);\n");
                    int evalDoAfterBody = _jspx_th_aui_script_1.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_aui_script_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_1);
                  return;
                }
                _jspx_tagPool_aui_script_use.reuse(_jspx_th_aui_script_1);
                out.write("\n\t\t\t\t\t\t\t");
              }
              if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
                return;
              }
              _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
              out.write("\n\t\t\t\t\t\t");
            }
            if (_jspx_th_c_choose_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
              return;
            }
            _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
            out.write("\n\t\t\t\t\t");
          }
          if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
            return;
          }
          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
          out.write("\n\t\t\t\t\t");
          if (_jspx_meth_c_otherwise_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_choose_0, _jspx_page_context))
            return;
          out.write("\n\t\t\t\t");
        }
        if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
          return;
        }
        _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
        out.write("\n\t\t\t</div>\n\t\t");
      }
      if (_jspx_th_liferay$1ui_panel_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1ui_panel_title_persistState_id_extended_collapsible.reuse(_jspx_th_liferay$1ui_panel_0);
        return;
      }
      _jspx_tagPool_liferay$1ui_panel_title_persistState_id_extended_collapsible.reuse(_jspx_th_liferay$1ui_panel_0);
      out.write("\n\t</div>\n\n");

}

      out.write('\n');
      out.write('\n');
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_4.setPageContext(_jspx_page_context);
      _jspx_th_c_if_4.setParent(null);
      _jspx_th_c_if_4.setTest( displayActivityCounterNames.length == 0 );
      int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
      if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t<div class=\"alert alert-info portlet-configuration\">\n\t\t<a href=\"");
        out.print( portletDisplay.getURLConfiguration() );
        out.write("\" onClick=\"");
        out.print( portletDisplay.getURLConfigurationJS() );
        out.write("\">\n\t\t\t");
        if (_jspx_meth_liferay$1ui_message_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_4, _jspx_page_context))
          return;
        out.write("\n\t\t</a>\n\t</div>\n");
      }
      if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
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

  private boolean _jspx_meth_liferay$1ui_message_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_col_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_1);
    _jspx_th_liferay$1ui_message_0.setKey("activities-by-area");
    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_1(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_col_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_3);
    _jspx_th_liferay$1ui_message_1.setKey("current-value");
    int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
    if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_2(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_col_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_3);
    _jspx_th_liferay$1ui_message_2.setKey("average-activities-per-day");
    int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
    if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_3(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_col_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_3 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_3.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_3);
    _jspx_th_liferay$1ui_message_3.setKey("highest-activity-period");
    int _jspx_eval_liferay$1ui_message_3 = _jspx_th_liferay$1ui_message_3.doStartTag();
    if (_jspx_th_liferay$1ui_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_3);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_4(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_col_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_4 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_4.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_3);
    _jspx_th_liferay$1ui_message_4.setKey("lowest-activity-period");
    int _jspx_eval_liferay$1ui_message_4 = _jspx_th_liferay$1ui_message_4.doStartTag();
    if (_jspx_th_liferay$1ui_message_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
    return false;
  }

  private boolean _jspx_meth_c_otherwise_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_choose_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_2 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
    _jspx_th_c_otherwise_2.setPageContext(_jspx_page_context);
    _jspx_th_c_otherwise_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
    int _jspx_eval_c_otherwise_2 = _jspx_th_c_otherwise_2.doStartTag();
    if (_jspx_eval_c_otherwise_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\n\t\t\t\t\t\t<div class=\"alert alert-info portlet-configuration\">\n\t\t\t\t\t\t\t");
      if (_jspx_meth_liferay$1ui_message_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_2, _jspx_page_context))
        return true;
      out.write("\n\t\t\t\t\t\t</div>\n\t\t\t\t\t");
    }
    if (_jspx_th_c_otherwise_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
      return true;
    }
    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_5 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_5.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
    _jspx_th_liferay$1ui_message_5.setKey("there-is-not-enough-data-to-display-for-this-counter");
    int _jspx_eval_liferay$1ui_message_5 = _jspx_th_liferay$1ui_message_5.doStartTag();
    if (_jspx_th_liferay$1ui_message_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_6 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_6.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
    _jspx_th_liferay$1ui_message_6.setKey("please-configure-this-widget-and-select-at-least-one-activity-counter");
    int _jspx_eval_liferay$1ui_message_6 = _jspx_th_liferay$1ui_message_6.doStartTag();
    if (_jspx_th_liferay$1ui_message_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_6);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_6);
    return false;
  }
}
