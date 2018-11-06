package org.apache.jsp.custom_005fattribute;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.*;
import com.liferay.portal.kernel.model.impl.*;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import java.io.Serializable;
import java.text.Format;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.service.permission.ExpandoColumnPermissionUtil;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

public final class page_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/custom_attribute/init.jsp");
    _jspx_dependants.add("/init.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_field$1wrapper_label;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1map_map$1display_name_longitude_latitude_geolocation_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_input$1date_yearValue_yearParam_name_monthValue_monthParam_firstDayOfWeek_disabled_dayValue_dayParam_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_input$1time_name_minuteValue_minuteParam_hourValue_hourParam_disabled_amPmValue_amPmParam_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_label_checked_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_field$1wrapper_name_label;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_input$1localized_xml_name_id_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_use;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_field$1wrapper_label = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1map_map$1display_name_longitude_latitude_geolocation_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_input$1date_yearValue_yearParam_name_monthValue_monthParam_firstDayOfWeek_disabled_dayValue_dayParam_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_input$1time_name_minuteValue_minuteParam_hourValue_hourParam_disabled_amPmValue_amPmParam_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_label_checked_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_field$1wrapper_name_label = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_input$1localized_xml_name_id_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_use = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_aui_input_value_type_name_nobody.release();
    _jspx_tagPool_aui_field$1wrapper_label.release();
    _jspx_tagPool_liferay$1map_map$1display_name_longitude_latitude_geolocation_nobody.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_liferay$1ui_input$1date_yearValue_yearParam_name_monthValue_monthParam_firstDayOfWeek_disabled_dayValue_dayParam_nobody.release();
    _jspx_tagPool_liferay$1ui_input$1time_name_minuteValue_minuteParam_hourValue_hourParam_disabled_amPmValue_amPmParam_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.release();
    _jspx_tagPool_aui_field$1wrapper_name_label.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_input$1localized_xml_name_id_cssClass_nobody.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_aui_script_use.release();
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
      out.write("\n\n\n\n\n");
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
      out.write('\n');
      out.write('\n');

String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_ui_custom_attribute_page") + StringPool.UNDERLINE;

String className = (String)request.getAttribute("liferay-expando:custom-attribute:className");
long classPK = GetterUtil.getLong((String)request.getAttribute("liferay-expando:custom-attribute:classPK"));
boolean editable = GetterUtil.getBoolean((String)request.getAttribute("liferay-expando:custom-attribute:editable"));
boolean label = GetterUtil.getBoolean((String)request.getAttribute("liferay-expando:custom-attribute:label"));
String name = (String)request.getAttribute("liferay-expando:custom-attribute:name");

ExpandoBridge expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(company.getCompanyId(), className, classPK);

      out.write('\n');
      out.write('\n');
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_0.setPageContext(_jspx_page_context);
      _jspx_th_c_if_0.setParent(null);
      _jspx_th_c_if_0.setTest( expandoBridge.hasAttribute(name) );
      int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
      if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\n\t");

	int type = expandoBridge.getAttributeType(name);
	Serializable value = expandoBridge.getAttribute(name);
	Serializable defaultValue = expandoBridge.getAttributeDefault(name);

	UnicodeProperties properties = expandoBridge.getAttributeProperties(name);

	boolean propertyHidden = GetterUtil.getBoolean(properties.get(ExpandoColumnConstants.PROPERTY_HIDDEN));
	boolean propertyVisibleWithUpdatePermission = GetterUtil.getBoolean(properties.get(ExpandoColumnConstants.PROPERTY_VISIBLE_WITH_UPDATE_PERMISSION));
	boolean propertySecret = GetterUtil.getBoolean(properties.getProperty(ExpandoColumnConstants.PROPERTY_SECRET));
	int propertyHeight = GetterUtil.getInteger(properties.getProperty(ExpandoColumnConstants.PROPERTY_HEIGHT));
	int propertyWidth = GetterUtil.getInteger(properties.getProperty(ExpandoColumnConstants.PROPERTY_WIDTH));
	String propertyDisplayType = GetterUtil.getString(properties.getProperty(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE), ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_TEXT_BOX);

	if (editable && propertyVisibleWithUpdatePermission) {
		propertyHidden = !ExpandoColumnPermissionUtil.contains(permissionChecker, company.getCompanyId(), className, ExpandoTableConstants.DEFAULT_TABLE_NAME, name, ActionKeys.UPDATE);
	}

	String localizedName = LanguageUtil.get(resourceBundle, name);

	if (name.equals(localizedName)) {
		localizedName = HtmlUtil.escape(TextFormatter.format(name, TextFormatter.J));
	}

	Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);
	
        out.write("\n\n\t");
        //  c:if
        com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
        _jspx_th_c_if_1.setPageContext(_jspx_page_context);
        _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
        _jspx_th_c_if_1.setTest( !propertyHidden && ExpandoColumnPermissionUtil.contains(permissionChecker, company.getCompanyId(), className, ExpandoTableConstants.DEFAULT_TABLE_NAME, name, ActionKeys.VIEW) );
        int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
        if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  c:choose
          com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
          _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
          _jspx_th_c_choose_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
          int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
          if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            //  c:when
            com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
            _jspx_th_c_when_0.setPageContext(_jspx_page_context);
            _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
            _jspx_th_c_when_0.setTest( editable && ExpandoColumnPermissionUtil.contains(permissionChecker, company.getCompanyId(), className, ExpandoTableConstants.DEFAULT_TABLE_NAME, name, ActionKeys.UPDATE) );
            int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
            if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t");
              //  aui:field-wrapper
              com.liferay.taglib.aui.FieldWrapperTag _jspx_th_aui_field$1wrapper_0 = (com.liferay.taglib.aui.FieldWrapperTag) _jspx_tagPool_aui_field$1wrapper_name_label.get(com.liferay.taglib.aui.FieldWrapperTag.class);
              _jspx_th_aui_field$1wrapper_0.setPageContext(_jspx_page_context);
              _jspx_th_aui_field$1wrapper_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
              _jspx_th_aui_field$1wrapper_0.setLabel( label ? localizedName : StringPool.BLANK );
              _jspx_th_aui_field$1wrapper_0.setName( randomNamespace + name );
              int _jspx_eval_aui_field$1wrapper_0 = _jspx_th_aui_field$1wrapper_0.doStartTag();
              if (_jspx_eval_aui_field$1wrapper_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t<input name=\"");
                if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_field$1wrapper_0, _jspx_page_context))
                  return;
                out.write("ExpandoAttributeName--");
                out.print( HtmlUtil.escapeAttribute(name) );
                out.write("--\" type=\"hidden\" value=\"");
                out.print( HtmlUtil.escapeAttribute(name) );
                out.write("\" />\n\n\t\t\t\t\t");
                //  c:choose
                com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
                _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_0);
                int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
                if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t");
                  //  c:when
                  com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                  _jspx_th_c_when_1.setPageContext(_jspx_page_context);
                  _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                  _jspx_th_c_when_1.setTest( type == ExpandoColumnConstants.BOOLEAN );
                  int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
                  if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\n\t\t\t\t\t\t\t");

							Boolean curValue = (Boolean)value;

							if (curValue == null) {
								curValue = (Boolean)defaultValue;
							}

							curValue = ParamUtil.getBoolean(request, "ExpandoAttribute--" + name + "--", curValue);
							
                    out.write("\n\n\t\t\t\t\t\t\t<select class=\"form-control\" id=\"");
                    if (_jspx_meth_portlet_namespace_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_1, _jspx_page_context))
                      return;
                    out.print( randomNamespace );
                    out.print( HtmlUtil.getAUICompatibleId(name) );
                    out.write("\" name=\"");
                    if (_jspx_meth_portlet_namespace_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_1, _jspx_page_context))
                      return;
                    out.write("ExpandoAttribute--");
                    out.print( HtmlUtil.escapeAttribute(name) );
                    out.write("--\">\n\t\t\t\t\t\t\t\t<option ");
                    out.print( curValue ? "selected" : "" );
                    out.write(" value=\"1\">");
                    //  liferay-ui:message
                    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                    _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                    _jspx_th_liferay$1ui_message_0.setKey( Boolean.TRUE.toString() );
                    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
                    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
                    out.write("</option>\n\t\t\t\t\t\t\t\t<option ");
                    out.print( !curValue ? "selected" : "" );
                    out.write(" value=\"0\">");
                    //  liferay-ui:message
                    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                    _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                    _jspx_th_liferay$1ui_message_1.setKey( Boolean.FALSE.toString() );
                    int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
                    if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
                    out.write("</option>\n\t\t\t\t\t\t\t</select>\n\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                    return;
                  }
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                  out.write("\n\t\t\t\t\t\t");
                  //  c:when
                  com.liferay.taglib.core.WhenTag _jspx_th_c_when_2 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                  _jspx_th_c_when_2.setPageContext(_jspx_page_context);
                  _jspx_th_c_when_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                  _jspx_th_c_when_2.setTest( type == ExpandoColumnConstants.BOOLEAN_ARRAY );
                  int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
                  if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_when_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                    return;
                  }
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                  out.write("\n\t\t\t\t\t\t");
                  //  c:when
                  com.liferay.taglib.core.WhenTag _jspx_th_c_when_3 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                  _jspx_th_c_when_3.setPageContext(_jspx_page_context);
                  _jspx_th_c_when_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                  _jspx_th_c_when_3.setTest( type == ExpandoColumnConstants.DATE );
                  int _jspx_eval_c_when_3 = _jspx_th_c_when_3.doStartTag();
                  if (_jspx_eval_c_when_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t<span id=\"");
                    if (_jspx_meth_portlet_namespace_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_3, _jspx_page_context))
                      return;
                    out.print( randomNamespace );
                    out.print( HtmlUtil.getAUICompatibleId(name) );
                    out.write("\">\n\n\t\t\t\t\t\t\t\t");

								Calendar valueDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

								if (value != null) {
									valueDate.setTime((Date)value);
								}
								else if (defaultValue != null) {
									valueDate.setTime((Date)defaultValue);
								}
								else {
									valueDate.setTime(new Date());
								}

								String fieldParam = "ExpandoAttribute--" + name + "--";

								int day = ParamUtil.getInteger(request, fieldParam + "Day", -1);

								if ((day == -1) && (valueDate != null)) {
									day = valueDate.get(Calendar.DATE);
								}

								int month = ParamUtil.getInteger(request, fieldParam + "Month", -1);

								if ((month == -1) && (valueDate != null)) {
									month = valueDate.get(Calendar.MONTH);
								}

								int year = ParamUtil.getInteger(request, fieldParam + "Year", -1);

								if ((year == -1) && (valueDate != null)) {
									year = valueDate.get(Calendar.YEAR);
								}

								int amPm = ParamUtil.getInteger(request, fieldParam + "AmPm", -1);

								if ((amPm == -1) && (valueDate != null)) {
									amPm = Calendar.AM;

									if (DateUtil.isFormatAmPm(locale)) {
										amPm = valueDate.get(Calendar.AM_PM);
									}
								}

								int hour = ParamUtil.getInteger(request, fieldParam + "Hour", -1);

								if ((hour == -1) && (valueDate != null)) {
									hour = valueDate.get(Calendar.HOUR_OF_DAY);

									if (DateUtil.isFormatAmPm(locale)) {
										hour = valueDate.get(Calendar.HOUR);
									}
								}

								int minute = ParamUtil.getInteger(request, fieldParam + "Minute", -1);

								if ((minute == -1) && (valueDate != null)) {
									minute = valueDate.get(Calendar.MINUTE);
								}
								
                    out.write("\n\n\t\t\t\t\t\t\t\t");
                    //  liferay-ui:input-date
                    com.liferay.taglib.ui.InputDateTag _jspx_th_liferay$1ui_input$1date_0 = (com.liferay.taglib.ui.InputDateTag) _jspx_tagPool_liferay$1ui_input$1date_yearValue_yearParam_name_monthValue_monthParam_firstDayOfWeek_disabled_dayValue_dayParam_nobody.get(com.liferay.taglib.ui.InputDateTag.class);
                    _jspx_th_liferay$1ui_input$1date_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_input$1date_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_3);
                    _jspx_th_liferay$1ui_input$1date_0.setDayParam( fieldParam + "Day" );
                    _jspx_th_liferay$1ui_input$1date_0.setDayValue( day );
                    _jspx_th_liferay$1ui_input$1date_0.setDisabled( false );
                    _jspx_th_liferay$1ui_input$1date_0.setFirstDayOfWeek( valueDate.getFirstDayOfWeek() - 1 );
                    _jspx_th_liferay$1ui_input$1date_0.setMonthParam( fieldParam + "Month" );
                    _jspx_th_liferay$1ui_input$1date_0.setMonthValue( month );
                    _jspx_th_liferay$1ui_input$1date_0.setName( fieldParam + "Date" );
                    _jspx_th_liferay$1ui_input$1date_0.setYearParam( fieldParam + "Year" );
                    _jspx_th_liferay$1ui_input$1date_0.setYearValue( year );
                    int _jspx_eval_liferay$1ui_input$1date_0 = _jspx_th_liferay$1ui_input$1date_0.doStartTag();
                    if (_jspx_th_liferay$1ui_input$1date_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_input$1date_yearValue_yearParam_name_monthValue_monthParam_firstDayOfWeek_disabled_dayValue_dayParam_nobody.reuse(_jspx_th_liferay$1ui_input$1date_0);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_input$1date_yearValue_yearParam_name_monthValue_monthParam_firstDayOfWeek_disabled_dayValue_dayParam_nobody.reuse(_jspx_th_liferay$1ui_input$1date_0);
                    out.write("\n\n\t\t\t\t\t\t\t\t");
                    //  liferay-ui:input-time
                    com.liferay.taglib.ui.InputTimeTag _jspx_th_liferay$1ui_input$1time_0 = (com.liferay.taglib.ui.InputTimeTag) _jspx_tagPool_liferay$1ui_input$1time_name_minuteValue_minuteParam_hourValue_hourParam_disabled_amPmValue_amPmParam_nobody.get(com.liferay.taglib.ui.InputTimeTag.class);
                    _jspx_th_liferay$1ui_input$1time_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_input$1time_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_3);
                    _jspx_th_liferay$1ui_input$1time_0.setAmPmParam( fieldParam + "AmPm" );
                    _jspx_th_liferay$1ui_input$1time_0.setAmPmValue( amPm );
                    _jspx_th_liferay$1ui_input$1time_0.setDisabled( false );
                    _jspx_th_liferay$1ui_input$1time_0.setHourParam( fieldParam + "Hour" );
                    _jspx_th_liferay$1ui_input$1time_0.setHourValue( hour );
                    _jspx_th_liferay$1ui_input$1time_0.setMinuteParam( fieldParam + "Minute" );
                    _jspx_th_liferay$1ui_input$1time_0.setMinuteValue( minute );
                    _jspx_th_liferay$1ui_input$1time_0.setName( fieldParam + "Time" );
                    int _jspx_eval_liferay$1ui_input$1time_0 = _jspx_th_liferay$1ui_input$1time_0.doStartTag();
                    if (_jspx_th_liferay$1ui_input$1time_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_input$1time_name_minuteValue_minuteParam_hourValue_hourParam_disabled_amPmValue_amPmParam_nobody.reuse(_jspx_th_liferay$1ui_input$1time_0);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_input$1time_name_minuteValue_minuteParam_hourValue_hourParam_disabled_amPmValue_amPmParam_nobody.reuse(_jspx_th_liferay$1ui_input$1time_0);
                    out.write("\n\t\t\t\t\t\t\t</span>\n\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_when_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
                    return;
                  }
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
                  out.write("\n\t\t\t\t\t\t");
                  //  c:when
                  com.liferay.taglib.core.WhenTag _jspx_th_c_when_4 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                  _jspx_th_c_when_4.setPageContext(_jspx_page_context);
                  _jspx_th_c_when_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                  _jspx_th_c_when_4.setTest( type == ExpandoColumnConstants.DATE_ARRAY );
                  int _jspx_eval_c_when_4 = _jspx_th_c_when_4.doStartTag();
                  if (_jspx_eval_c_when_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_when_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
                    return;
                  }
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
                  out.write("\n\t\t\t\t\t\t");
                  //  c:when
                  com.liferay.taglib.core.WhenTag _jspx_th_c_when_5 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                  _jspx_th_c_when_5.setPageContext(_jspx_page_context);
                  _jspx_th_c_when_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                  _jspx_th_c_when_5.setTest( type == ExpandoColumnConstants.DOUBLE_ARRAY );
                  int _jspx_eval_c_when_5 = _jspx_th_c_when_5.doStartTag();
                  if (_jspx_eval_c_when_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\n\t\t\t\t\t\t\t");

							double[] curValue = ParamUtil.getDoubleValues(request, "ExpandoAttribute--" + name + "--", (double[])value);
							
                    out.write("\n\n\t\t\t\t\t\t\t");
                    //  c:choose
                    com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_2 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                    _jspx_th_c_choose_2.setPageContext(_jspx_page_context);
                    _jspx_th_c_choose_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_5);
                    int _jspx_eval_c_choose_2 = _jspx_th_c_choose_2.doStartTag();
                    if (_jspx_eval_c_choose_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_6 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_6.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                      _jspx_th_c_when_6.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_CHECKBOX) );
                      int _jspx_eval_c_when_6 = _jspx_th_c_when_6.doStartTag();
                      if (_jspx_eval_c_when_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									for (double curDefaultValue : (double[])defaultValue) {
									
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t");
                        //  aui:input
                        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_0 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody.get(com.liferay.taglib.aui.InputTag.class);
                        _jspx_th_aui_input_0.setPageContext(_jspx_page_context);
                        _jspx_th_aui_input_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_6);
                        _jspx_th_aui_input_0.setChecked( (curValue.length > 0) && ArrayUtil.contains(curValue, curDefaultValue) );
                        _jspx_th_aui_input_0.setId( StringUtil.randomId() );
                        _jspx_th_aui_input_0.setLabel( String.valueOf(curDefaultValue) );
                        _jspx_th_aui_input_0.setName( "ExpandoAttribute--" + HtmlUtil.escapeAttribute(name) + "--" );
                        _jspx_th_aui_input_0.setType("checkbox");
                        _jspx_th_aui_input_0.setValue( curDefaultValue );
                        int _jspx_eval_aui_input_0 = _jspx_th_aui_input_0.doStartTag();
                        if (_jspx_th_aui_input_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody.reuse(_jspx_th_aui_input_0);
                          return;
                        }
                        _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody.reuse(_jspx_th_aui_input_0);
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									}
									
                        out.write("\n\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_6);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_6);
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_7 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_7.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                      _jspx_th_c_when_7.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_RADIO) );
                      int _jspx_eval_c_when_7 = _jspx_th_c_when_7.doStartTag();
                      if (_jspx_eval_c_when_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									for (double curDefaultValue : (double[])defaultValue) {
									
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t");
                        //  aui:input
                        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_1 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.get(com.liferay.taglib.aui.InputTag.class);
                        _jspx_th_aui_input_1.setPageContext(_jspx_page_context);
                        _jspx_th_aui_input_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_7);
                        _jspx_th_aui_input_1.setChecked( (curValue.length > 0) && ArrayUtil.contains(curValue, curDefaultValue) );
                        _jspx_th_aui_input_1.setLabel( String.valueOf(curDefaultValue) );
                        _jspx_th_aui_input_1.setName( "ExpandoAttribute--" + HtmlUtil.escapeAttribute(name) + "--" );
                        _jspx_th_aui_input_1.setType("radio");
                        _jspx_th_aui_input_1.setValue( curDefaultValue );
                        int _jspx_eval_aui_input_1 = _jspx_th_aui_input_1.doStartTag();
                        if (_jspx_th_aui_input_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_1);
                          return;
                        }
                        _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_1);
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									}
									
                        out.write("\n\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_7);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_7);
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_8 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_8.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                      _jspx_th_c_when_8.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_SELECTION_LIST) );
                      int _jspx_eval_c_when_8 = _jspx_th_c_when_8.doStartTag();
                      if (_jspx_eval_c_when_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t<select class=\"form-control\" id=\"");
                        if (_jspx_meth_portlet_namespace_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_8, _jspx_page_context))
                          return;
                        out.print( randomNamespace );
                        out.print( HtmlUtil.getAUICompatibleId(name) );
                        out.write("\" name=\"");
                        if (_jspx_meth_portlet_namespace_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_8, _jspx_page_context))
                          return;
                        out.write("ExpandoAttribute--");
                        out.print( HtmlUtil.escapeAttribute(name) );
                        out.write("--\">\n\n\t\t\t\t\t\t\t\t\t\t");

										for (double curDefaultValue : (double[])defaultValue) {
										
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t<option ");
                        out.print( ((curValue.length > 0) && ArrayUtil.contains(curValue, curDefaultValue)) ? "selected" : "" );
                        out.write('>');
                        out.print( curDefaultValue );
                        out.write("</option>\n\n\t\t\t\t\t\t\t\t\t\t");

										}
										
                        out.write("\n\n\t\t\t\t\t\t\t\t\t</select>\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_8);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_8);
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_9 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_9.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                      _jspx_th_c_when_9.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_TEXT_BOX) );
                      int _jspx_eval_c_when_9 = _jspx_th_c_when_9.doStartTag();
                      if (_jspx_eval_c_when_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									if (curValue.length == 0) {
										curValue = (double[])defaultValue;
									}
									
                        out.write("\n\n\t\t\t\t\t\t\t\t\t<textarea class=\"field form-control lfr-textarea\" id=\"");
                        if (_jspx_meth_portlet_namespace_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_9, _jspx_page_context))
                          return;
                        out.print( randomNamespace );
                        out.print( HtmlUtil.getAUICompatibleId(name) );
                        out.write("\" name=\"");
                        if (_jspx_meth_portlet_namespace_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_9, _jspx_page_context))
                          return;
                        out.write("ExpandoAttribute--");
                        out.print( HtmlUtil.escapeAttribute(name) );
                        out.write("--\">");
                        out.print( StringUtil.merge(curValue, StringPool.NEW_LINE) );
                        out.write("</textarea>\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_9);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_9);
                      out.write("\n\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_choose_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
                      return;
                    }
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
                    out.write("\n\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_when_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_5);
                    return;
                  }
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_5);
                  out.write("\n\t\t\t\t\t\t");
                  //  c:when
                  com.liferay.taglib.core.WhenTag _jspx_th_c_when_10 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                  _jspx_th_c_when_10.setPageContext(_jspx_page_context);
                  _jspx_th_c_when_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                  _jspx_th_c_when_10.setTest( type == ExpandoColumnConstants.FLOAT_ARRAY );
                  int _jspx_eval_c_when_10 = _jspx_th_c_when_10.doStartTag();
                  if (_jspx_eval_c_when_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\n\t\t\t\t\t\t\t");

							float[] curValue = ParamUtil.getFloatValues(request, "ExpandoAttribute--" + name + "--", (float[])value);
							
                    out.write("\n\n\t\t\t\t\t\t\t");
                    //  c:choose
                    com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_3 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                    _jspx_th_c_choose_3.setPageContext(_jspx_page_context);
                    _jspx_th_c_choose_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_10);
                    int _jspx_eval_c_choose_3 = _jspx_th_c_choose_3.doStartTag();
                    if (_jspx_eval_c_choose_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_11 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_11.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
                      _jspx_th_c_when_11.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_CHECKBOX) );
                      int _jspx_eval_c_when_11 = _jspx_th_c_when_11.doStartTag();
                      if (_jspx_eval_c_when_11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									for (float curDefaultValue : (float[])defaultValue) {
									
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t");
                        //  aui:input
                        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_2 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody.get(com.liferay.taglib.aui.InputTag.class);
                        _jspx_th_aui_input_2.setPageContext(_jspx_page_context);
                        _jspx_th_aui_input_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_11);
                        _jspx_th_aui_input_2.setChecked( (curValue.length > 0) && ArrayUtil.contains(curValue, curDefaultValue) );
                        _jspx_th_aui_input_2.setId( StringUtil.randomId() );
                        _jspx_th_aui_input_2.setLabel( String.valueOf(curDefaultValue) );
                        _jspx_th_aui_input_2.setName( "ExpandoAttribute--" + HtmlUtil.escapeAttribute(name) + "--" );
                        _jspx_th_aui_input_2.setType("checkbox");
                        _jspx_th_aui_input_2.setValue( curDefaultValue );
                        int _jspx_eval_aui_input_2 = _jspx_th_aui_input_2.doStartTag();
                        if (_jspx_th_aui_input_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody.reuse(_jspx_th_aui_input_2);
                          return;
                        }
                        _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody.reuse(_jspx_th_aui_input_2);
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									}
									
                        out.write("\n\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_11);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_11);
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_12 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_12.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
                      _jspx_th_c_when_12.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_RADIO) );
                      int _jspx_eval_c_when_12 = _jspx_th_c_when_12.doStartTag();
                      if (_jspx_eval_c_when_12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									for (float curDefaultValue : (float[])defaultValue) {
									
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t");
                        //  aui:input
                        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_3 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.get(com.liferay.taglib.aui.InputTag.class);
                        _jspx_th_aui_input_3.setPageContext(_jspx_page_context);
                        _jspx_th_aui_input_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_12);
                        _jspx_th_aui_input_3.setChecked( (curValue.length > 0) && ArrayUtil.contains(curValue, curDefaultValue) );
                        _jspx_th_aui_input_3.setLabel( String.valueOf(curDefaultValue) );
                        _jspx_th_aui_input_3.setName( "ExpandoAttribute--" + HtmlUtil.escapeAttribute(name) + "--" );
                        _jspx_th_aui_input_3.setType("radio");
                        _jspx_th_aui_input_3.setValue( curDefaultValue );
                        int _jspx_eval_aui_input_3 = _jspx_th_aui_input_3.doStartTag();
                        if (_jspx_th_aui_input_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_3);
                          return;
                        }
                        _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_3);
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									}
									
                        out.write("\n\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_12);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_12);
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_13 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_13.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
                      _jspx_th_c_when_13.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_SELECTION_LIST) );
                      int _jspx_eval_c_when_13 = _jspx_th_c_when_13.doStartTag();
                      if (_jspx_eval_c_when_13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t<select class=\"form-control\" id=\"");
                        if (_jspx_meth_portlet_namespace_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_13, _jspx_page_context))
                          return;
                        out.print( randomNamespace );
                        out.print( HtmlUtil.getAUICompatibleId(name) );
                        out.write("\" name=\"");
                        if (_jspx_meth_portlet_namespace_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_13, _jspx_page_context))
                          return;
                        out.write("ExpandoAttribute--");
                        out.print( HtmlUtil.escapeAttribute(name) );
                        out.write("--\">\n\n\t\t\t\t\t\t\t\t\t\t");

										for (float curDefaultValue : (float[])defaultValue) {
										
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t<option ");
                        out.print( ((curValue.length > 0) && ArrayUtil.contains(curValue, curDefaultValue)) ? "selected" : "" );
                        out.write('>');
                        out.print( curDefaultValue );
                        out.write("</option>\n\n\t\t\t\t\t\t\t\t\t\t");

										}
										
                        out.write("\n\n\t\t\t\t\t\t\t\t\t</select>\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_13);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_13);
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_14 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_14.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
                      _jspx_th_c_when_14.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_TEXT_BOX) );
                      int _jspx_eval_c_when_14 = _jspx_th_c_when_14.doStartTag();
                      if (_jspx_eval_c_when_14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									if (curValue.length == 0) {
										curValue = (float[])defaultValue;
									}
									
                        out.write("\n\n\t\t\t\t\t\t\t\t\t<textarea class=\"field form-control lfr-textarea\" id=\"");
                        if (_jspx_meth_portlet_namespace_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_14, _jspx_page_context))
                          return;
                        out.print( randomNamespace );
                        out.print( HtmlUtil.getAUICompatibleId(name) );
                        out.write("\" name=\"");
                        if (_jspx_meth_portlet_namespace_11((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_14, _jspx_page_context))
                          return;
                        out.write("ExpandoAttribute--");
                        out.print( HtmlUtil.escapeAttribute(name) );
                        out.write("--\">");
                        out.print( StringUtil.merge(curValue, StringPool.NEW_LINE) );
                        out.write("</textarea>\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_14);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_14);
                      out.write("\n\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_choose_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_3);
                      return;
                    }
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_3);
                    out.write("\n\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_when_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_10);
                    return;
                  }
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_10);
                  out.write("\n\t\t\t\t\t\t");
                  //  c:when
                  com.liferay.taglib.core.WhenTag _jspx_th_c_when_15 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                  _jspx_th_c_when_15.setPageContext(_jspx_page_context);
                  _jspx_th_c_when_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                  _jspx_th_c_when_15.setTest( type == ExpandoColumnConstants.GEOLOCATION );
                  int _jspx_eval_c_when_15 = _jspx_th_c_when_15.doStartTag();
                  if (_jspx_eval_c_when_15 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\n\t\t\t\t\t\t\t");

							JSONObject geolocationJSONObject = JSONFactoryUtil.createJSONObject(value.toString());
							
                    out.write("\n\n\t\t\t\t\t\t\t<div id=\"");
                    if (_jspx_meth_portlet_namespace_12((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_15, _jspx_page_context))
                      return;
                    out.write("CoordinatesContainer\">\n\t\t\t\t\t\t\t\t<div class=\"glyphicon glyphicon-map-marker\" id=\"");
                    out.print( portletDisplay.getNamespace()+"ExpandoAttribute--" + name + "--Location" );
                    out.write("\">\n\t\t\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t\t\t");
                    //  liferay-map:map-display
                    com.liferay.map.taglib.servlet.taglib.MapDisplayTag _jspx_th_liferay$1map_map$1display_0 = (com.liferay.map.taglib.servlet.taglib.MapDisplayTag) _jspx_tagPool_liferay$1map_map$1display_name_longitude_latitude_geolocation_nobody.get(com.liferay.map.taglib.servlet.taglib.MapDisplayTag.class);
                    _jspx_th_liferay$1map_map$1display_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1map_map$1display_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_15);
                    _jspx_th_liferay$1map_map$1display_0.setGeolocation( true );
                    _jspx_th_liferay$1map_map$1display_0.setLatitude( geolocationJSONObject.getDouble("latitude", 0) );
                    _jspx_th_liferay$1map_map$1display_0.setLongitude( geolocationJSONObject.getDouble("longitude", 0) );
                    _jspx_th_liferay$1map_map$1display_0.setName( "ExpandoAttribute--" + name +"--" );
                    int _jspx_eval_liferay$1map_map$1display_0 = _jspx_th_liferay$1map_map$1display_0.doStartTag();
                    if (_jspx_th_liferay$1map_map$1display_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1map_map$1display_name_longitude_latitude_geolocation_nobody.reuse(_jspx_th_liferay$1map_map$1display_0);
                      return;
                    }
                    _jspx_tagPool_liferay$1map_map$1display_name_longitude_latitude_geolocation_nobody.reuse(_jspx_th_liferay$1map_map$1display_0);
                    out.write("\n\t\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t\t");
                    //  aui:script
                    com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
                    _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
                    _jspx_th_aui_script_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_15);
                    _jspx_th_aui_script_0.setUse("liferay-map-base");
                    int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
                    if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                        out = _jspx_page_context.pushBody();
                        _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                        _jspx_th_aui_script_0.doInitBody();
                      }
                      do {
                        out.write("\n\t\t\t\t\t\t\t\tvar inputName = \"");
                        out.print( portletDisplay.getNamespace()+"ExpandoAttribute--" + HtmlUtil.escapeJS(name) + "--" );
                        out.write("\";\n\n\t\t\t\t\t\t\t\tvar geolocationField = {\n\t\t\t\t\t\t\t\t\tinit: function() {\n\t\t\t\t\t\t\t\t\t\tLiferay.MapBase.get(\n\t\t\t\t\t\t\t\t\t\t\tinputName,\n\t\t\t\t\t\t\t\t\t\t\tfunction(map) {\n\t\t\t\t\t\t\t\t\t\t\t\tmap.on('positionChange', geolocationField.onPositionChange, geolocationField);\n\t\t\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\t\t);\n\t\t\t\t\t\t\t\t\t},\n\t\t\t\t\t\t\t\t\tonPositionChange: function(event) {\n\t\t\t\t\t\t\t\t\t\tvar location = event.newVal.location;\n\n\t\t\t\t\t\t\t\t\t\tvar inputNode = document.querySelector('[name=\"' + inputName + '\"]');\n\n\t\t\t\t\t\t\t\t\t\tif (inputNode) {\n\t\t\t\t\t\t\t\t\t\t\tinputNode.setAttribute(\n\t\t\t\t\t\t\t\t\t\t\t\t'value',\n\t\t\t\t\t\t\t\t\t\t\t\tJSON.stringify(\n\t\t\t\t\t\t\t\t\t\t\t\t\t{\n\t\t\t\t\t\t\t\t\t\t\t\t\t\tlatitude: location.lat,\n\t\t\t\t\t\t\t\t\t\t\t\t\t\tlongitude: location.lng\n\t\t\t\t\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\t\t\t\t)\n\t\t\t\t\t\t\t\t\t\t\t);\n\t\t\t\t\t\t\t\t\t\t}\n\n\t\t\t\t\t\t\t\t\t\tvar locationNode = document.getElementById(inputName + 'Location');\n\n\t\t\t\t\t\t\t\t\t\tif (locationNode) {\n\t\t\t\t\t\t\t\t\t\t\tlocationNode.innerHTML = event.newVal.address;\n\t\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t\t}\n\t\t\t\t\t\t\t\t};\n\n\t\t\t\t\t\t\t\tgeolocationField.init();\n\t\t\t\t\t\t\t");
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
                    out.write("\n\n\t\t\t\t\t\t\t");
                    //  aui:input
                    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_4 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
                    _jspx_th_aui_input_4.setPageContext(_jspx_page_context);
                    _jspx_th_aui_input_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_15);
                    _jspx_th_aui_input_4.setName( "ExpandoAttribute--" + name + "--" );
                    _jspx_th_aui_input_4.setType("hidden");
                    _jspx_th_aui_input_4.setValue( HtmlUtil.escape(value.toString()) );
                    int _jspx_eval_aui_input_4 = _jspx_th_aui_input_4.doStartTag();
                    if (_jspx_th_aui_input_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_4);
                      return;
                    }
                    _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_4);
                    out.write("\n\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_when_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_15);
                    return;
                  }
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_15);
                  out.write("\n\t\t\t\t\t\t");
                  //  c:when
                  com.liferay.taglib.core.WhenTag _jspx_th_c_when_16 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                  _jspx_th_c_when_16.setPageContext(_jspx_page_context);
                  _jspx_th_c_when_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                  _jspx_th_c_when_16.setTest( type == ExpandoColumnConstants.INTEGER_ARRAY );
                  int _jspx_eval_c_when_16 = _jspx_th_c_when_16.doStartTag();
                  if (_jspx_eval_c_when_16 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\n\t\t\t\t\t\t\t");

							int[] curValue = ParamUtil.getIntegerValues(request, "ExpandoAttribute--" + name + "--", (int[])value);
							
                    out.write("\n\n\t\t\t\t\t\t\t");
                    //  c:choose
                    com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_4 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                    _jspx_th_c_choose_4.setPageContext(_jspx_page_context);
                    _jspx_th_c_choose_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_16);
                    int _jspx_eval_c_choose_4 = _jspx_th_c_choose_4.doStartTag();
                    if (_jspx_eval_c_choose_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_17 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_17.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_4);
                      _jspx_th_c_when_17.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_CHECKBOX) );
                      int _jspx_eval_c_when_17 = _jspx_th_c_when_17.doStartTag();
                      if (_jspx_eval_c_when_17 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									for (int curDefaultValue : (int[])defaultValue) {
									
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t");
                        //  aui:input
                        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_5 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody.get(com.liferay.taglib.aui.InputTag.class);
                        _jspx_th_aui_input_5.setPageContext(_jspx_page_context);
                        _jspx_th_aui_input_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_17);
                        _jspx_th_aui_input_5.setChecked( (curValue.length > 0) && ArrayUtil.contains(curValue, curDefaultValue) );
                        _jspx_th_aui_input_5.setId( StringUtil.randomId() );
                        _jspx_th_aui_input_5.setLabel( String.valueOf(curDefaultValue) );
                        _jspx_th_aui_input_5.setName( "ExpandoAttribute--" + HtmlUtil.escapeAttribute(name) + "--" );
                        _jspx_th_aui_input_5.setType("checkbox");
                        _jspx_th_aui_input_5.setValue( curDefaultValue );
                        int _jspx_eval_aui_input_5 = _jspx_th_aui_input_5.doStartTag();
                        if (_jspx_th_aui_input_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody.reuse(_jspx_th_aui_input_5);
                          return;
                        }
                        _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody.reuse(_jspx_th_aui_input_5);
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									}
									
                        out.write("\n\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_17);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_17);
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_18 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_18.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_4);
                      _jspx_th_c_when_18.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_RADIO) );
                      int _jspx_eval_c_when_18 = _jspx_th_c_when_18.doStartTag();
                      if (_jspx_eval_c_when_18 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									for (int curDefaultValue : (int[])defaultValue) {
									
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t");
                        //  aui:input
                        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_6 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.get(com.liferay.taglib.aui.InputTag.class);
                        _jspx_th_aui_input_6.setPageContext(_jspx_page_context);
                        _jspx_th_aui_input_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_18);
                        _jspx_th_aui_input_6.setChecked( (curValue.length > 0) && ArrayUtil.contains(curValue, curDefaultValue) );
                        _jspx_th_aui_input_6.setLabel( String.valueOf(curDefaultValue) );
                        _jspx_th_aui_input_6.setName( "ExpandoAttribute--" + HtmlUtil.escapeAttribute(name) + "--" );
                        _jspx_th_aui_input_6.setType("radio");
                        _jspx_th_aui_input_6.setValue( curDefaultValue );
                        int _jspx_eval_aui_input_6 = _jspx_th_aui_input_6.doStartTag();
                        if (_jspx_th_aui_input_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_6);
                          return;
                        }
                        _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_6);
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									}
									
                        out.write("\n\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_18);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_18);
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_19 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_19.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_4);
                      _jspx_th_c_when_19.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_SELECTION_LIST) );
                      int _jspx_eval_c_when_19 = _jspx_th_c_when_19.doStartTag();
                      if (_jspx_eval_c_when_19 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t<select class=\"form-control\" id=\"");
                        if (_jspx_meth_portlet_namespace_13((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_19, _jspx_page_context))
                          return;
                        out.print( randomNamespace );
                        out.print( HtmlUtil.getAUICompatibleId(name) );
                        out.write("\" name=\"");
                        if (_jspx_meth_portlet_namespace_14((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_19, _jspx_page_context))
                          return;
                        out.write("ExpandoAttribute--");
                        out.print( HtmlUtil.escapeAttribute(name) );
                        out.write("--\">\n\n\t\t\t\t\t\t\t\t\t\t");

										for (int curDefaultValue : (int[])defaultValue) {
										
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t<option ");
                        out.print( ((curValue.length > 0) && ArrayUtil.contains(curValue, curDefaultValue)) ? "selected" : "" );
                        out.write('>');
                        out.print( curDefaultValue );
                        out.write("</option>\n\n\t\t\t\t\t\t\t\t\t\t");

										}
										
                        out.write("\n\n\t\t\t\t\t\t\t\t\t</select>\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_19);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_19);
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_20 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_20.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_4);
                      _jspx_th_c_when_20.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_TEXT_BOX) );
                      int _jspx_eval_c_when_20 = _jspx_th_c_when_20.doStartTag();
                      if (_jspx_eval_c_when_20 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									if (curValue.length == 0) {
										curValue = (int[])defaultValue;
									}
									
                        out.write("\n\n\t\t\t\t\t\t\t\t\t<textarea class=\"field form-control lfr-textarea\" id=\"");
                        if (_jspx_meth_portlet_namespace_15((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_20, _jspx_page_context))
                          return;
                        out.print( randomNamespace );
                        out.print( HtmlUtil.getAUICompatibleId(name) );
                        out.write("\" name=\"");
                        if (_jspx_meth_portlet_namespace_16((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_20, _jspx_page_context))
                          return;
                        out.write("ExpandoAttribute--");
                        out.print( HtmlUtil.escapeAttribute(name) );
                        out.write("--\">");
                        out.print( StringUtil.merge(curValue, StringPool.NEW_LINE) );
                        out.write("</textarea>\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_20);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_20);
                      out.write("\n\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_choose_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_4);
                      return;
                    }
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_4);
                    out.write("\n\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_when_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_16);
                    return;
                  }
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_16);
                  out.write("\n\t\t\t\t\t\t");
                  //  c:when
                  com.liferay.taglib.core.WhenTag _jspx_th_c_when_21 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                  _jspx_th_c_when_21.setPageContext(_jspx_page_context);
                  _jspx_th_c_when_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                  _jspx_th_c_when_21.setTest( type == ExpandoColumnConstants.LONG_ARRAY );
                  int _jspx_eval_c_when_21 = _jspx_th_c_when_21.doStartTag();
                  if (_jspx_eval_c_when_21 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\n\t\t\t\t\t\t\t");

							long[] curValue = ParamUtil.getLongValues(request, "ExpandoAttribute--" + name + "--", (long[])value);
							
                    out.write("\n\n\t\t\t\t\t\t\t");
                    //  c:choose
                    com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_5 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                    _jspx_th_c_choose_5.setPageContext(_jspx_page_context);
                    _jspx_th_c_choose_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_21);
                    int _jspx_eval_c_choose_5 = _jspx_th_c_choose_5.doStartTag();
                    if (_jspx_eval_c_choose_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_22 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_22.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_5);
                      _jspx_th_c_when_22.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_CHECKBOX) );
                      int _jspx_eval_c_when_22 = _jspx_th_c_when_22.doStartTag();
                      if (_jspx_eval_c_when_22 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									for (long curDefaultValue : (long[])defaultValue) {
									
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t");
                        //  aui:input
                        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_7 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody.get(com.liferay.taglib.aui.InputTag.class);
                        _jspx_th_aui_input_7.setPageContext(_jspx_page_context);
                        _jspx_th_aui_input_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_22);
                        _jspx_th_aui_input_7.setChecked( (curValue.length > 0) && ArrayUtil.contains(curValue, curDefaultValue) );
                        _jspx_th_aui_input_7.setId( StringUtil.randomId() );
                        _jspx_th_aui_input_7.setLabel( String.valueOf(curDefaultValue) );
                        _jspx_th_aui_input_7.setName( "ExpandoAttribute--" + HtmlUtil.escapeAttribute(name) + "--" );
                        _jspx_th_aui_input_7.setType("checkbox");
                        _jspx_th_aui_input_7.setValue( curDefaultValue );
                        int _jspx_eval_aui_input_7 = _jspx_th_aui_input_7.doStartTag();
                        if (_jspx_th_aui_input_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody.reuse(_jspx_th_aui_input_7);
                          return;
                        }
                        _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody.reuse(_jspx_th_aui_input_7);
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									}
									
                        out.write("\n\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_22);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_22);
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_23 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_23.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_5);
                      _jspx_th_c_when_23.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_RADIO) );
                      int _jspx_eval_c_when_23 = _jspx_th_c_when_23.doStartTag();
                      if (_jspx_eval_c_when_23 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									for (long curDefaultValue : (long[])defaultValue) {
									
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t");
                        //  aui:input
                        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_8 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.get(com.liferay.taglib.aui.InputTag.class);
                        _jspx_th_aui_input_8.setPageContext(_jspx_page_context);
                        _jspx_th_aui_input_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_23);
                        _jspx_th_aui_input_8.setChecked( (curValue.length > 0) && ArrayUtil.contains(curValue, curDefaultValue) );
                        _jspx_th_aui_input_8.setLabel( String.valueOf(curDefaultValue) );
                        _jspx_th_aui_input_8.setName( "ExpandoAttribute--" + HtmlUtil.escapeAttribute(name) + "--" );
                        _jspx_th_aui_input_8.setType("radio");
                        _jspx_th_aui_input_8.setValue( curDefaultValue );
                        int _jspx_eval_aui_input_8 = _jspx_th_aui_input_8.doStartTag();
                        if (_jspx_th_aui_input_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_8);
                          return;
                        }
                        _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_8);
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									}
									
                        out.write("\n\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_23);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_23);
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_24 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_24.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_5);
                      _jspx_th_c_when_24.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_SELECTION_LIST) );
                      int _jspx_eval_c_when_24 = _jspx_th_c_when_24.doStartTag();
                      if (_jspx_eval_c_when_24 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t<select class=\"form-control\" id=\"");
                        if (_jspx_meth_portlet_namespace_17((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_24, _jspx_page_context))
                          return;
                        out.print( randomNamespace );
                        out.print( HtmlUtil.getAUICompatibleId(name) );
                        out.write("\" name=\"");
                        if (_jspx_meth_portlet_namespace_18((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_24, _jspx_page_context))
                          return;
                        out.write("ExpandoAttribute--");
                        out.print( HtmlUtil.escapeAttribute(name) );
                        out.write("--\">\n\n\t\t\t\t\t\t\t\t\t\t");

										for (long curDefaultValue : (long[])defaultValue) {
										
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t<option ");
                        out.print( ((curValue.length > 0) && ArrayUtil.contains(curValue, curDefaultValue)) ? "selected" : "" );
                        out.write('>');
                        out.print( curDefaultValue );
                        out.write("</option>\n\n\t\t\t\t\t\t\t\t\t\t");

										}
										
                        out.write("\n\n\t\t\t\t\t\t\t\t\t</select>\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_24);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_24);
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_25 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_25.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_25.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_5);
                      _jspx_th_c_when_25.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_TEXT_BOX) );
                      int _jspx_eval_c_when_25 = _jspx_th_c_when_25.doStartTag();
                      if (_jspx_eval_c_when_25 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									if (curValue.length == 0) {
										curValue = (long[])defaultValue;
									}
									
                        out.write("\n\n\t\t\t\t\t\t\t\t\t<textarea class=\"field form-control lfr-textarea\" id=\"");
                        if (_jspx_meth_portlet_namespace_19((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_25, _jspx_page_context))
                          return;
                        out.print( randomNamespace );
                        out.print( HtmlUtil.getAUICompatibleId(name) );
                        out.write("\" name=\"");
                        if (_jspx_meth_portlet_namespace_20((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_25, _jspx_page_context))
                          return;
                        out.write("ExpandoAttribute--");
                        out.print( HtmlUtil.escapeAttribute(name) );
                        out.write("--\">");
                        out.print( StringUtil.merge(curValue, StringPool.NEW_LINE) );
                        out.write("</textarea>\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_25);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_25);
                      out.write("\n\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_choose_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_5);
                      return;
                    }
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_5);
                    out.write("\n\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_when_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_21);
                    return;
                  }
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_21);
                  out.write("\n\t\t\t\t\t\t");
                  //  c:when
                  com.liferay.taglib.core.WhenTag _jspx_th_c_when_26 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                  _jspx_th_c_when_26.setPageContext(_jspx_page_context);
                  _jspx_th_c_when_26.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                  _jspx_th_c_when_26.setTest( type == ExpandoColumnConstants.NUMBER_ARRAY );
                  int _jspx_eval_c_when_26 = _jspx_th_c_when_26.doStartTag();
                  if (_jspx_eval_c_when_26 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\n\t\t\t\t\t\t\t");

							Number[] curValue = ParamUtil.getNumberValues(request, "ExpandoAttribute--" + name + "--", (Number[])value);
							
                    out.write("\n\n\t\t\t\t\t\t\t");
                    //  c:choose
                    com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_6 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                    _jspx_th_c_choose_6.setPageContext(_jspx_page_context);
                    _jspx_th_c_choose_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_26);
                    int _jspx_eval_c_choose_6 = _jspx_th_c_choose_6.doStartTag();
                    if (_jspx_eval_c_choose_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_27 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_27.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_27.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_6);
                      _jspx_th_c_when_27.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_CHECKBOX) );
                      int _jspx_eval_c_when_27 = _jspx_th_c_when_27.doStartTag();
                      if (_jspx_eval_c_when_27 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									for (Number curDefaultValue : (Number[])defaultValue) {
									
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t");
                        //  aui:input
                        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_9 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody.get(com.liferay.taglib.aui.InputTag.class);
                        _jspx_th_aui_input_9.setPageContext(_jspx_page_context);
                        _jspx_th_aui_input_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_27);
                        _jspx_th_aui_input_9.setChecked( (curValue.length > 0) && ArrayUtil.contains(curValue, curDefaultValue) );
                        _jspx_th_aui_input_9.setId( StringUtil.randomId() );
                        _jspx_th_aui_input_9.setLabel( String.valueOf(curDefaultValue) );
                        _jspx_th_aui_input_9.setName( "ExpandoAttribute--" + HtmlUtil.escapeAttribute(name) + "--" );
                        _jspx_th_aui_input_9.setType("checkbox");
                        _jspx_th_aui_input_9.setValue( curDefaultValue );
                        int _jspx_eval_aui_input_9 = _jspx_th_aui_input_9.doStartTag();
                        if (_jspx_th_aui_input_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody.reuse(_jspx_th_aui_input_9);
                          return;
                        }
                        _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody.reuse(_jspx_th_aui_input_9);
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									}
									
                        out.write("\n\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_27);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_27);
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_28 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_28.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_28.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_6);
                      _jspx_th_c_when_28.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_RADIO) );
                      int _jspx_eval_c_when_28 = _jspx_th_c_when_28.doStartTag();
                      if (_jspx_eval_c_when_28 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									for (Number curDefaultValue : (Number[])defaultValue) {
									
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t");
                        //  aui:input
                        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_10 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.get(com.liferay.taglib.aui.InputTag.class);
                        _jspx_th_aui_input_10.setPageContext(_jspx_page_context);
                        _jspx_th_aui_input_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_28);
                        _jspx_th_aui_input_10.setChecked( (curValue.length > 0) && ArrayUtil.contains(curValue, curDefaultValue) );
                        _jspx_th_aui_input_10.setLabel( String.valueOf(curDefaultValue) );
                        _jspx_th_aui_input_10.setName( "ExpandoAttribute--" + HtmlUtil.escapeAttribute(name) + "--" );
                        _jspx_th_aui_input_10.setType("radio");
                        _jspx_th_aui_input_10.setValue( curDefaultValue );
                        int _jspx_eval_aui_input_10 = _jspx_th_aui_input_10.doStartTag();
                        if (_jspx_th_aui_input_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_10);
                          return;
                        }
                        _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_10);
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									}
									
                        out.write("\n\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_28.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_28);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_28);
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_29 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_29.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_29.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_6);
                      _jspx_th_c_when_29.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_SELECTION_LIST) );
                      int _jspx_eval_c_when_29 = _jspx_th_c_when_29.doStartTag();
                      if (_jspx_eval_c_when_29 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t<select class=\"form-control\" id=\"");
                        if (_jspx_meth_portlet_namespace_21((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_29, _jspx_page_context))
                          return;
                        out.print( randomNamespace );
                        out.print( HtmlUtil.getAUICompatibleId(name) );
                        out.write("\" name=\"");
                        if (_jspx_meth_portlet_namespace_22((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_29, _jspx_page_context))
                          return;
                        out.write("ExpandoAttribute--");
                        out.print( HtmlUtil.escapeAttribute(name) );
                        out.write("--\">\n\n\t\t\t\t\t\t\t\t\t\t");

										for (Number curDefaultValue : (Number[])defaultValue) {
										
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t<option ");
                        out.print( ((curValue.length > 0) && ArrayUtil.contains(curValue, curDefaultValue)) ? "selected" : "" );
                        out.write('>');
                        out.print( curDefaultValue );
                        out.write("</option>\n\n\t\t\t\t\t\t\t\t\t\t");

										}
										
                        out.write("\n\n\t\t\t\t\t\t\t\t\t</select>\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_29.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_29);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_29);
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_30 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_30.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_30.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_6);
                      _jspx_th_c_when_30.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_TEXT_BOX) );
                      int _jspx_eval_c_when_30 = _jspx_th_c_when_30.doStartTag();
                      if (_jspx_eval_c_when_30 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									if (curValue.length == 0) {
										curValue = (Number[])defaultValue;
									}
									
                        out.write("\n\n\t\t\t\t\t\t\t\t\t<textarea class=\"field form-control lfr-textarea\" id=\"");
                        if (_jspx_meth_portlet_namespace_23((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_30, _jspx_page_context))
                          return;
                        out.print( randomNamespace );
                        out.print( HtmlUtil.getAUICompatibleId(name) );
                        out.write("\" name=\"");
                        if (_jspx_meth_portlet_namespace_24((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_30, _jspx_page_context))
                          return;
                        out.write("ExpandoAttribute--");
                        out.print( HtmlUtil.escapeAttribute(name) );
                        out.write("--\">");
                        out.print( StringUtil.merge(curValue, StringPool.NEW_LINE) );
                        out.write("</textarea>\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_30.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_30);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_30);
                      out.write("\n\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_choose_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_6);
                      return;
                    }
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_6);
                    out.write("\n\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_when_26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_26);
                    return;
                  }
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_26);
                  out.write("\n\t\t\t\t\t\t");
                  //  c:when
                  com.liferay.taglib.core.WhenTag _jspx_th_c_when_31 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                  _jspx_th_c_when_31.setPageContext(_jspx_page_context);
                  _jspx_th_c_when_31.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                  _jspx_th_c_when_31.setTest( type == ExpandoColumnConstants.SHORT_ARRAY );
                  int _jspx_eval_c_when_31 = _jspx_th_c_when_31.doStartTag();
                  if (_jspx_eval_c_when_31 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\n\t\t\t\t\t\t\t");

							short[] curValue = ParamUtil.getShortValues(request, "ExpandoAttribute--" + name + "--", (short[])value);
							
                    out.write("\n\n\t\t\t\t\t\t\t");
                    //  c:choose
                    com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_7 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                    _jspx_th_c_choose_7.setPageContext(_jspx_page_context);
                    _jspx_th_c_choose_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_31);
                    int _jspx_eval_c_choose_7 = _jspx_th_c_choose_7.doStartTag();
                    if (_jspx_eval_c_choose_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_32 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_32.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_32.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_7);
                      _jspx_th_c_when_32.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_CHECKBOX) );
                      int _jspx_eval_c_when_32 = _jspx_th_c_when_32.doStartTag();
                      if (_jspx_eval_c_when_32 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									for (short curDefaultValue : (short[])defaultValue) {
									
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t");
                        //  aui:input
                        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_11 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody.get(com.liferay.taglib.aui.InputTag.class);
                        _jspx_th_aui_input_11.setPageContext(_jspx_page_context);
                        _jspx_th_aui_input_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_32);
                        _jspx_th_aui_input_11.setChecked( (curValue.length > 0) && ArrayUtil.contains(curValue, curDefaultValue) );
                        _jspx_th_aui_input_11.setId( StringUtil.randomId() );
                        _jspx_th_aui_input_11.setLabel( String.valueOf(curDefaultValue) );
                        _jspx_th_aui_input_11.setName( "ExpandoAttribute--" + HtmlUtil.escapeAttribute(name) + "--" );
                        _jspx_th_aui_input_11.setType("checkbox");
                        _jspx_th_aui_input_11.setValue( curDefaultValue );
                        int _jspx_eval_aui_input_11 = _jspx_th_aui_input_11.doStartTag();
                        if (_jspx_th_aui_input_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody.reuse(_jspx_th_aui_input_11);
                          return;
                        }
                        _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody.reuse(_jspx_th_aui_input_11);
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									}
									
                        out.write("\n\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_32.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_32);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_32);
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_33 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_33.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_33.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_7);
                      _jspx_th_c_when_33.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_RADIO) );
                      int _jspx_eval_c_when_33 = _jspx_th_c_when_33.doStartTag();
                      if (_jspx_eval_c_when_33 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									for (short curDefaultValue : (short[])defaultValue) {
									
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t");
                        //  aui:input
                        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_12 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.get(com.liferay.taglib.aui.InputTag.class);
                        _jspx_th_aui_input_12.setPageContext(_jspx_page_context);
                        _jspx_th_aui_input_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_33);
                        _jspx_th_aui_input_12.setChecked( (curValue.length > 0) && ArrayUtil.contains(curValue, curDefaultValue) );
                        _jspx_th_aui_input_12.setLabel( String.valueOf(curDefaultValue) );
                        _jspx_th_aui_input_12.setName( "ExpandoAttribute--" + HtmlUtil.escapeAttribute(name) + "--" );
                        _jspx_th_aui_input_12.setType("radio");
                        _jspx_th_aui_input_12.setValue( curDefaultValue );
                        int _jspx_eval_aui_input_12 = _jspx_th_aui_input_12.doStartTag();
                        if (_jspx_th_aui_input_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_12);
                          return;
                        }
                        _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_12);
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									}
									
                        out.write("\n\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_33.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_33);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_33);
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_34 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_34.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_34.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_7);
                      _jspx_th_c_when_34.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_SELECTION_LIST) );
                      int _jspx_eval_c_when_34 = _jspx_th_c_when_34.doStartTag();
                      if (_jspx_eval_c_when_34 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t<select class=\"form-control\" id=\"");
                        if (_jspx_meth_portlet_namespace_25((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_34, _jspx_page_context))
                          return;
                        out.print( randomNamespace );
                        out.print( HtmlUtil.getAUICompatibleId(name) );
                        out.write("\" name=\"");
                        if (_jspx_meth_portlet_namespace_26((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_34, _jspx_page_context))
                          return;
                        out.write("ExpandoAttribute--");
                        out.print( HtmlUtil.escapeAttribute(name) );
                        out.write("--\">\n\n\t\t\t\t\t\t\t\t\t\t");

										for (short curDefaultValue : (short[])defaultValue) {
										
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t<option ");
                        out.print( ((curValue.length > 0) && ArrayUtil.contains(curValue, curDefaultValue)) ? "selected" : "" );
                        out.write('>');
                        out.print( curDefaultValue );
                        out.write("</option>\n\n\t\t\t\t\t\t\t\t\t\t");

										}
										
                        out.write("\n\n\t\t\t\t\t\t\t\t\t</select>\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_34.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_34);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_34);
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_35 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_35.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_35.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_7);
                      _jspx_th_c_when_35.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_TEXT_BOX) );
                      int _jspx_eval_c_when_35 = _jspx_th_c_when_35.doStartTag();
                      if (_jspx_eval_c_when_35 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									if (curValue.length == 0) {
										curValue = (short[])defaultValue;
									}
									
                        out.write("\n\n\t\t\t\t\t\t\t\t\t<textarea class=\"field form-control lfr-textarea\" id=\"");
                        if (_jspx_meth_portlet_namespace_27((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_35, _jspx_page_context))
                          return;
                        out.print( randomNamespace );
                        out.print( HtmlUtil.getAUICompatibleId(name) );
                        out.write("\" name=\"");
                        if (_jspx_meth_portlet_namespace_28((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_35, _jspx_page_context))
                          return;
                        out.write("ExpandoAttribute--");
                        out.print( HtmlUtil.escapeAttribute(name) );
                        out.write("--\">");
                        out.print( StringUtil.merge(curValue, StringPool.NEW_LINE) );
                        out.write("</textarea>\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_35.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_35);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_35);
                      out.write("\n\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_choose_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_7);
                      return;
                    }
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_7);
                    out.write("\n\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_when_31.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_31);
                    return;
                  }
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_31);
                  out.write("\n\t\t\t\t\t\t");
                  //  c:when
                  com.liferay.taglib.core.WhenTag _jspx_th_c_when_36 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                  _jspx_th_c_when_36.setPageContext(_jspx_page_context);
                  _jspx_th_c_when_36.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                  _jspx_th_c_when_36.setTest( type == ExpandoColumnConstants.STRING_ARRAY );
                  int _jspx_eval_c_when_36 = _jspx_th_c_when_36.doStartTag();
                  if (_jspx_eval_c_when_36 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\n\t\t\t\t\t\t\t");

							String[] curValue = ParamUtil.getStringValues(request, "ExpandoAttribute--" + name + "--", (String[])value);
							
                    out.write("\n\n\t\t\t\t\t\t\t");
                    //  c:choose
                    com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_8 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                    _jspx_th_c_choose_8.setPageContext(_jspx_page_context);
                    _jspx_th_c_choose_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_36);
                    int _jspx_eval_c_choose_8 = _jspx_th_c_choose_8.doStartTag();
                    if (_jspx_eval_c_choose_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_37 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_37.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_37.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_8);
                      _jspx_th_c_when_37.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_CHECKBOX) );
                      int _jspx_eval_c_when_37 = _jspx_th_c_when_37.doStartTag();
                      if (_jspx_eval_c_when_37 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									for (String curDefaultValue : (String[])defaultValue) {
									
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t");
                        //  aui:input
                        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_13 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody.get(com.liferay.taglib.aui.InputTag.class);
                        _jspx_th_aui_input_13.setPageContext(_jspx_page_context);
                        _jspx_th_aui_input_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_37);
                        _jspx_th_aui_input_13.setChecked( (curValue.length > 0) && ArrayUtil.contains(curValue, curDefaultValue) );
                        _jspx_th_aui_input_13.setId( StringUtil.randomId() );
                        _jspx_th_aui_input_13.setLabel( String.valueOf(curDefaultValue) );
                        _jspx_th_aui_input_13.setName( "ExpandoAttribute--" + HtmlUtil.escapeAttribute(name) + "--" );
                        _jspx_th_aui_input_13.setType("checkbox");
                        _jspx_th_aui_input_13.setValue( curDefaultValue );
                        int _jspx_eval_aui_input_13 = _jspx_th_aui_input_13.doStartTag();
                        if (_jspx_th_aui_input_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody.reuse(_jspx_th_aui_input_13);
                          return;
                        }
                        _jspx_tagPool_aui_input_value_type_name_label_id_checked_nobody.reuse(_jspx_th_aui_input_13);
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									}
									
                        out.write("\n\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_37.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_37);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_37);
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_38 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_38.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_38.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_8);
                      _jspx_th_c_when_38.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_RADIO) );
                      int _jspx_eval_c_when_38 = _jspx_th_c_when_38.doStartTag();
                      if (_jspx_eval_c_when_38 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									for (String curDefaultValue : (String[])defaultValue) {
									
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t");
                        //  aui:input
                        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_14 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.get(com.liferay.taglib.aui.InputTag.class);
                        _jspx_th_aui_input_14.setPageContext(_jspx_page_context);
                        _jspx_th_aui_input_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_38);
                        _jspx_th_aui_input_14.setChecked( (curValue.length > 0) && ArrayUtil.contains(curValue, curDefaultValue) );
                        _jspx_th_aui_input_14.setLabel( curDefaultValue );
                        _jspx_th_aui_input_14.setName( "ExpandoAttribute--" + HtmlUtil.escapeAttribute(name) + "--" );
                        _jspx_th_aui_input_14.setType("radio");
                        _jspx_th_aui_input_14.setValue( curDefaultValue );
                        int _jspx_eval_aui_input_14 = _jspx_th_aui_input_14.doStartTag();
                        if (_jspx_th_aui_input_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_14);
                          return;
                        }
                        _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_14);
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									}
									
                        out.write("\n\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_38.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_38);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_38);
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_39 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_39.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_39.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_8);
                      _jspx_th_c_when_39.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_SELECTION_LIST) );
                      int _jspx_eval_c_when_39 = _jspx_th_c_when_39.doStartTag();
                      if (_jspx_eval_c_when_39 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t<select class=\"form-control\" id=\"");
                        if (_jspx_meth_portlet_namespace_29((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_39, _jspx_page_context))
                          return;
                        out.print( randomNamespace );
                        out.print( HtmlUtil.getAUICompatibleId(name) );
                        out.write("\" name=\"");
                        if (_jspx_meth_portlet_namespace_30((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_39, _jspx_page_context))
                          return;
                        out.write("ExpandoAttribute--");
                        out.print( HtmlUtil.escapeAttribute(name) );
                        out.write("--\">\n\n\t\t\t\t\t\t\t\t\t\t");

										for (String curDefaultValue : (String[])defaultValue) {
										
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t\t<option ");
                        out.print( ((curValue.length > 0) && ArrayUtil.contains(curValue, curDefaultValue)) ? "selected" : "" );
                        out.write(" value=\"");
                        out.print( HtmlUtil.escape(curDefaultValue) );
                        out.write('"');
                        out.write('>');
                        out.print( HtmlUtil.escape(curDefaultValue) );
                        out.write("</option>\n\n\t\t\t\t\t\t\t\t\t\t");

										}
										
                        out.write("\n\n\t\t\t\t\t\t\t\t\t</select>\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_39.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_39);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_39);
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_40 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_40.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_40.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_8);
                      _jspx_th_c_when_40.setTest( propertyDisplayType.equals(ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE_TEXT_BOX) );
                      int _jspx_eval_c_when_40 = _jspx_th_c_when_40.doStartTag();
                      if (_jspx_eval_c_when_40 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\n\t\t\t\t\t\t\t\t\t");

									if (curValue.length == 0) {
										curValue = (String[])defaultValue;
									}
									
                        out.write("\n\n\t\t\t\t\t\t\t\t\t<textarea class=\"field form-control lfr-textarea\" id=\"");
                        if (_jspx_meth_portlet_namespace_31((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_40, _jspx_page_context))
                          return;
                        out.print( randomNamespace );
                        out.print( HtmlUtil.getAUICompatibleId(name) );
                        out.write("\" name=\"");
                        if (_jspx_meth_portlet_namespace_32((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_40, _jspx_page_context))
                          return;
                        out.write("ExpandoAttribute--");
                        out.print( HtmlUtil.escapeAttribute(name) );
                        out.write("--\">");
                        out.print( HtmlUtil.escape(StringUtil.merge(curValue, StringPool.NEW_LINE)) );
                        out.write("</textarea>\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_40.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_40);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_40);
                      out.write("\n\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_choose_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_8);
                      return;
                    }
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_8);
                    out.write("\n\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_when_36.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_36);
                    return;
                  }
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_36);
                  out.write("\n\t\t\t\t\t\t");
                  //  c:when
                  com.liferay.taglib.core.WhenTag _jspx_th_c_when_41 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                  _jspx_th_c_when_41.setPageContext(_jspx_page_context);
                  _jspx_th_c_when_41.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                  _jspx_th_c_when_41.setTest( type == ExpandoColumnConstants.STRING_LOCALIZED );
                  int _jspx_eval_c_when_41 = _jspx_th_c_when_41.doStartTag();
                  if (_jspx_eval_c_when_41 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\n\t\t\t\t\t\t\t");

							String xml = ParamUtil.getString(request, "ExpandoAttribute--" + name + "--");

							if (Validator.isNull(xml) && (value != null)) {
								xml = LocalizationUtil.updateLocalization((Map<Locale, String>)value, StringPool.BLANK, "Data", LocaleUtil.toLanguageId(locale));
							}

							if (Validator.isNull(xml) && (defaultValue != null)) {
								xml = LocalizationUtil.updateLocalization((Map<Locale, String>)defaultValue, StringPool.BLANK, "Data", LocaleUtil.toLanguageId(locale));
							}
							
                    out.write("\n\n\t\t\t\t\t\t\t");
                    //  liferay-ui:input-localized
                    com.liferay.taglib.ui.InputLocalizedTag _jspx_th_liferay$1ui_input$1localized_0 = (com.liferay.taglib.ui.InputLocalizedTag) _jspx_tagPool_liferay$1ui_input$1localized_xml_name_id_cssClass_nobody.get(com.liferay.taglib.ui.InputLocalizedTag.class);
                    _jspx_th_liferay$1ui_input$1localized_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_input$1localized_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_41);
                    _jspx_th_liferay$1ui_input$1localized_0.setCssClass("lfr-input-text");
                    _jspx_th_liferay$1ui_input$1localized_0.setId( randomNamespace + name );
                    _jspx_th_liferay$1ui_input$1localized_0.setName( "ExpandoAttribute--" + name + "--" );
                    _jspx_th_liferay$1ui_input$1localized_0.setXml( xml );
                    int _jspx_eval_liferay$1ui_input$1localized_0 = _jspx_th_liferay$1ui_input$1localized_0.doStartTag();
                    if (_jspx_th_liferay$1ui_input$1localized_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_input$1localized_xml_name_id_cssClass_nobody.reuse(_jspx_th_liferay$1ui_input$1localized_0);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_input$1localized_xml_name_id_cssClass_nobody.reuse(_jspx_th_liferay$1ui_input$1localized_0);
                    out.write("\n\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_when_41.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_41);
                    return;
                  }
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_41);
                  out.write("\n\t\t\t\t\t\t");
                  //  c:otherwise
                  com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                  _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
                  _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                  int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
                  if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\n\t\t\t\t\t\t\t");

							value = ParamUtil.getString(request, "ExpandoAttribute--" + name + "--", String.valueOf(value));

							if (Validator.isNull(String.valueOf(value))) {
								value = defaultValue;
							}
							
                    out.write("\n\n\t\t\t\t\t\t\t");
                    //  c:choose
                    com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_9 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                    _jspx_th_c_choose_9.setPageContext(_jspx_page_context);
                    _jspx_th_c_choose_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
                    int _jspx_eval_c_choose_9 = _jspx_th_c_choose_9.doStartTag();
                    if (_jspx_eval_c_choose_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_42 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_42.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_42.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_9);
                      _jspx_th_c_when_42.setTest( propertyHeight > 0 );
                      int _jspx_eval_c_when_42 = _jspx_th_c_when_42.doStartTag();
                      if (_jspx_eval_c_when_42 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t<textarea\n\t\t\t\t\t\t\t\t\t\tclass=\"field form-control lfr-input-text\"\n\t\t\t\t\t\t\t\t\t\tid=\"");
                        if (_jspx_meth_portlet_namespace_33((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_42, _jspx_page_context))
                          return;
                        out.print( randomNamespace );
                        out.print( HtmlUtil.getAUICompatibleId(name) );
                        out.write("\"\n\t\t\t\t\t\t\t\t\t\tname=\"");
                        if (_jspx_meth_portlet_namespace_34((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_42, _jspx_page_context))
                          return;
                        out.write("ExpandoAttribute--");
                        out.print( HtmlUtil.escapeAttribute(name) );
                        out.write("--\"\n\t\t\t\t\t\t\t\t\t\tstyle=\"\n\t\t\t\t\t\t\t\t\t\t");
                        //  c:if
                        com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                        _jspx_th_c_if_2.setPageContext(_jspx_page_context);
                        _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_42);
                        _jspx_th_c_if_2.setTest( propertyHeight > 0 );
                        int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
                        if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t\t\t\theight: ");
                          out.print( propertyHeight );
                          out.write("px;\n\t\t\t\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                          return;
                        }
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                        out.write("\n\n\t\t\t\t\t\t\t\t\t\t");
                        //  c:if
                        com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                        _jspx_th_c_if_3.setPageContext(_jspx_page_context);
                        _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_42);
                        _jspx_th_c_if_3.setTest( propertyWidth > 0 );
                        int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
                        if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t\t\t\twidth: ");
                          out.print( propertyWidth );
                          out.write("px;\n\t\t\t\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                          return;
                        }
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                        out.write("\"\n\t\t\t\t\t\t\t\t\t>");
                        out.print( HtmlUtil.escape(String.valueOf(value)) );
                        out.write("</textarea>\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_42.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_42);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_42);
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:otherwise
                      com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_1 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                      _jspx_th_c_otherwise_1.setPageContext(_jspx_page_context);
                      _jspx_th_c_otherwise_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_9);
                      int _jspx_eval_c_otherwise_1 = _jspx_th_c_otherwise_1.doStartTag();
                      if (_jspx_eval_c_otherwise_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t<input\n\t\t\t\t\t\t\t\t\t\tclass=\"field form-control lfr-input-text\"\n\t\t\t\t\t\t\t\t\t\tid=\"");
                        if (_jspx_meth_portlet_namespace_35((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_1, _jspx_page_context))
                          return;
                        out.print( randomNamespace );
                        out.print( HtmlUtil.getAUICompatibleId(name) );
                        out.write("\"\n\t\t\t\t\t\t\t\t\t\tname=\"");
                        if (_jspx_meth_portlet_namespace_36((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_1, _jspx_page_context))
                          return;
                        out.write("ExpandoAttribute--");
                        out.print( HtmlUtil.escapeAttribute(name) );
                        out.write("--\"\n\t\t\t\t\t\t\t\t\t\tstyle=\"\n\t\t\t\t\t\t\t\t\t\t");
                        //  c:if
                        com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                        _jspx_th_c_if_4.setPageContext(_jspx_page_context);
                        _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
                        _jspx_th_c_if_4.setTest( propertyWidth > 0 );
                        int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
                        if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t\t\t\twidth: ");
                          out.print( propertyWidth );
                          out.write("px;\n\t\t\t\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                          return;
                        }
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                        out.write("\"\n\t\t\t\t\t\t\t\t\t\ttype=\"");
                        out.print( propertySecret ? "password" : "text" );
                        out.write("\" value=\"");
                        out.print( HtmlUtil.escape(String.valueOf(value)) );
                        out.write("\"\n\t\t\t\t\t\t\t\t\t/>\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_otherwise_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                        return;
                      }
                      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                      out.write("\n\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_choose_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_9);
                      return;
                    }
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_9);
                    out.write("\n\t\t\t\t\t\t");
                  }
                  if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
                    return;
                  }
                  _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
                  out.write("\n\t\t\t\t\t");
                }
                if (_jspx_th_c_choose_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                  return;
                }
                _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_aui_field$1wrapper_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_field$1wrapper_name_label.reuse(_jspx_th_aui_field$1wrapper_0);
                return;
              }
              _jspx_tagPool_aui_field$1wrapper_name_label.reuse(_jspx_th_aui_field$1wrapper_0);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
              return;
            }
            _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
            out.write("\n\t\t\t");
            //  c:otherwise
            com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_2 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
            _jspx_th_c_otherwise_2.setPageContext(_jspx_page_context);
            _jspx_th_c_otherwise_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
            int _jspx_eval_c_otherwise_2 = _jspx_th_c_otherwise_2.doStartTag();
            if (_jspx_eval_c_otherwise_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\n\t\t\t\t");

				StringBundler sb = new StringBundler();

				if (type == ExpandoColumnConstants.BOOLEAN) {
					sb.append((Boolean)value);
				}
				else if (type == ExpandoColumnConstants.BOOLEAN_ARRAY) {
					if (!Arrays.equals((boolean[])value, (boolean[])defaultValue)) {
						sb.append(StringUtil.merge((boolean[])value));
					}
				}
				else if (type == ExpandoColumnConstants.DATE) {
					sb.append(dateFormatDateTime.format((Date)value));
				}
				else if (type == ExpandoColumnConstants.DATE_ARRAY) {
					if (!Arrays.deepEquals((Date[])value, (Date[])defaultValue)) {
						Date[] dates = (Date[])value;

						for (int i = 0; i < dates.length; i++) {
							if (i != 0) {
								sb.append(StringPool.COMMA_AND_SPACE);
							}

							sb.append(dateFormatDateTime.format(dates[i]));
						}
					}
				}
				else if (type == ExpandoColumnConstants.DOUBLE) {
					sb.append((Double)value);
				}
				else if (type == ExpandoColumnConstants.DOUBLE_ARRAY) {
					sb.append(StringUtil.merge((double[])value));
				}
				else if (type == ExpandoColumnConstants.FLOAT) {
					sb.append((Float)value);
				}
				else if (type == ExpandoColumnConstants.FLOAT_ARRAY) {
					sb.append(StringUtil.merge((float[])value));
				}
				else if (type == ExpandoColumnConstants.GEOLOCATION) {
					sb.append(value.toString());
				}
				else if (type == ExpandoColumnConstants.INTEGER) {
					sb.append((Integer)value);
				}
				else if (type == ExpandoColumnConstants.INTEGER_ARRAY) {
					sb.append(StringUtil.merge((int[])value));
				}
				else if (type == ExpandoColumnConstants.LONG) {
					sb.append((Long)value);
				}
				else if (type == ExpandoColumnConstants.LONG_ARRAY) {
					sb.append(StringUtil.merge((long[])value));
				}
				else if (type == ExpandoColumnConstants.NUMBER) {
					sb.append((Number)value);
				}
				else if (type == ExpandoColumnConstants.NUMBER_ARRAY) {
					sb.append(StringUtil.merge((Number[])value));
				}
				else if (type == ExpandoColumnConstants.SHORT) {
					sb.append((Short)value);
				}
				else if (type == ExpandoColumnConstants.SHORT_ARRAY) {
					sb.append(StringUtil.merge((short[])value));
				}
				else if (type == ExpandoColumnConstants.STRING_ARRAY) {
					sb.append(StringUtil.merge((String[])value));
				}
				else if (type == ExpandoColumnConstants.STRING_LOCALIZED) {
					Map<Locale, String> values = (Map<Locale, String>)value;

					sb.append(values.get(locale));
				}
				else {
					sb.append((String)value);
				}
				
              out.write("\n\n\t\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_5 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_5.setPageContext(_jspx_page_context);
              _jspx_th_c_if_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
              _jspx_th_c_if_5.setTest( (type != ExpandoColumnConstants.GEOLOCATION) && (editable || Validator.isNotNull(sb.toString())) );
              int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
              if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                //  aui:field-wrapper
                com.liferay.taglib.aui.FieldWrapperTag _jspx_th_aui_field$1wrapper_1 = (com.liferay.taglib.aui.FieldWrapperTag) _jspx_tagPool_aui_field$1wrapper_label.get(com.liferay.taglib.aui.FieldWrapperTag.class);
                _jspx_th_aui_field$1wrapper_1.setPageContext(_jspx_page_context);
                _jspx_th_aui_field$1wrapper_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
                _jspx_th_aui_field$1wrapper_1.setLabel( label ? localizedName : StringPool.BLANK );
                int _jspx_eval_aui_field$1wrapper_1 = _jspx_th_aui_field$1wrapper_1.doStartTag();
                if (_jspx_eval_aui_field$1wrapper_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t<span id=\"");
                  out.print( randomNamespace );
                  out.print( HtmlUtil.getAUICompatibleId(name) );
                  out.write('"');
                  out.write('>');
                  out.print( HtmlUtil.escape(sb.toString()) );
                  out.write("</span>\n\t\t\t\t\t");
                }
                if (_jspx_th_aui_field$1wrapper_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_field$1wrapper_label.reuse(_jspx_th_aui_field$1wrapper_1);
                  return;
                }
                _jspx_tagPool_aui_field$1wrapper_label.reuse(_jspx_th_aui_field$1wrapper_1);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
              out.write("\n\n\t\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_6 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_6.setPageContext(_jspx_page_context);
              _jspx_th_c_if_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
              _jspx_th_c_if_6.setTest( (type == ExpandoColumnConstants.GEOLOCATION) && (editable || Validator.isNotNull(sb.toString())) );
              int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
              if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\n\t\t\t\t\t");

					JSONObject geolocationJSONObject = JSONFactoryUtil.createJSONObject(value.toString());
					
                out.write("\n\n\t\t\t\t\t<div id=\"");
                if (_jspx_meth_portlet_namespace_37((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_6, _jspx_page_context))
                  return;
                out.write("CoordinatesContainer\">\n\t\t\t\t\t\t<div class=\"glyphicon glyphicon-map-marker\" id=\"");
                out.print( portletDisplay.getNamespace()+"ExpandoAttribute--" + name + "--Location" );
                out.write("\">\n\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t");
                //  liferay-map:map-display
                com.liferay.map.taglib.servlet.taglib.MapDisplayTag _jspx_th_liferay$1map_map$1display_1 = (com.liferay.map.taglib.servlet.taglib.MapDisplayTag) _jspx_tagPool_liferay$1map_map$1display_name_longitude_latitude_geolocation_nobody.get(com.liferay.map.taglib.servlet.taglib.MapDisplayTag.class);
                _jspx_th_liferay$1map_map$1display_1.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1map_map$1display_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
                _jspx_th_liferay$1map_map$1display_1.setGeolocation( true );
                _jspx_th_liferay$1map_map$1display_1.setLatitude( geolocationJSONObject.getDouble("latitude", 0) );
                _jspx_th_liferay$1map_map$1display_1.setLongitude( geolocationJSONObject.getDouble("longitude", 0) );
                _jspx_th_liferay$1map_map$1display_1.setName( "ExpandoAttribute--" + name +"--" );
                int _jspx_eval_liferay$1map_map$1display_1 = _jspx_th_liferay$1map_map$1display_1.doStartTag();
                if (_jspx_th_liferay$1map_map$1display_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1map_map$1display_name_longitude_latitude_geolocation_nobody.reuse(_jspx_th_liferay$1map_map$1display_1);
                  return;
                }
                _jspx_tagPool_liferay$1map_map$1display_name_longitude_latitude_geolocation_nobody.reuse(_jspx_th_liferay$1map_map$1display_1);
                out.write("\n\t\t\t\t\t</div>\n\t\t\t\t");
              }
              if (_jspx_th_c_if_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_c_otherwise_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
              return;
            }
            _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
            out.write("\n\t\t");
          }
          if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
            return;
          }
          _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
          return;
        }
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
        out.write('\n');
      }
      if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
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

  private boolean _jspx_meth_portlet_namespace_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_field$1wrapper_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_0 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_0);
    int _jspx_eval_portlet_namespace_0 = _jspx_th_portlet_namespace_0.doStartTag();
    if (_jspx_th_portlet_namespace_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_1 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_1.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
    int _jspx_eval_portlet_namespace_1 = _jspx_th_portlet_namespace_1.doStartTag();
    if (_jspx_th_portlet_namespace_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_1);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_1);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_2 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_2.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
    int _jspx_eval_portlet_namespace_2 = _jspx_th_portlet_namespace_2.doStartTag();
    if (_jspx_th_portlet_namespace_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_2);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_2);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_3 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_3.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_3);
    int _jspx_eval_portlet_namespace_3 = _jspx_th_portlet_namespace_3.doStartTag();
    if (_jspx_th_portlet_namespace_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_3);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_3);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_4 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_4.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_8);
    int _jspx_eval_portlet_namespace_4 = _jspx_th_portlet_namespace_4.doStartTag();
    if (_jspx_th_portlet_namespace_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_4);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_4);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_5 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_5.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_8);
    int _jspx_eval_portlet_namespace_5 = _jspx_th_portlet_namespace_5.doStartTag();
    if (_jspx_th_portlet_namespace_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_5);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_5);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_9, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_6 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_6.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_9);
    int _jspx_eval_portlet_namespace_6 = _jspx_th_portlet_namespace_6.doStartTag();
    if (_jspx_th_portlet_namespace_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_6);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_6);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_7(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_9, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_7 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_7.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_9);
    int _jspx_eval_portlet_namespace_7 = _jspx_th_portlet_namespace_7.doStartTag();
    if (_jspx_th_portlet_namespace_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_7);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_7);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_8(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_13, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_8 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_8.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_13);
    int _jspx_eval_portlet_namespace_8 = _jspx_th_portlet_namespace_8.doStartTag();
    if (_jspx_th_portlet_namespace_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_8);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_8);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_9(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_13, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_9 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_9.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_13);
    int _jspx_eval_portlet_namespace_9 = _jspx_th_portlet_namespace_9.doStartTag();
    if (_jspx_th_portlet_namespace_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_9);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_9);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_10(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_14, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_10 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_10.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_14);
    int _jspx_eval_portlet_namespace_10 = _jspx_th_portlet_namespace_10.doStartTag();
    if (_jspx_th_portlet_namespace_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_10);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_10);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_11(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_14, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_11 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_11.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_14);
    int _jspx_eval_portlet_namespace_11 = _jspx_th_portlet_namespace_11.doStartTag();
    if (_jspx_th_portlet_namespace_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_11);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_11);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_12(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_15, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_12 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_12.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_15);
    int _jspx_eval_portlet_namespace_12 = _jspx_th_portlet_namespace_12.doStartTag();
    if (_jspx_th_portlet_namespace_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_12);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_12);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_13(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_19, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_13 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_13.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_19);
    int _jspx_eval_portlet_namespace_13 = _jspx_th_portlet_namespace_13.doStartTag();
    if (_jspx_th_portlet_namespace_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_13);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_13);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_14(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_19, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_14 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_14.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_19);
    int _jspx_eval_portlet_namespace_14 = _jspx_th_portlet_namespace_14.doStartTag();
    if (_jspx_th_portlet_namespace_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_14);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_14);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_15(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_20, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_15 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_15.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_20);
    int _jspx_eval_portlet_namespace_15 = _jspx_th_portlet_namespace_15.doStartTag();
    if (_jspx_th_portlet_namespace_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_15);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_15);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_16(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_20, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_16 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_16.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_20);
    int _jspx_eval_portlet_namespace_16 = _jspx_th_portlet_namespace_16.doStartTag();
    if (_jspx_th_portlet_namespace_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_16);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_16);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_17(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_24, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_17 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_17.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_24);
    int _jspx_eval_portlet_namespace_17 = _jspx_th_portlet_namespace_17.doStartTag();
    if (_jspx_th_portlet_namespace_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_17);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_17);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_18(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_24, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_18 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_18.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_24);
    int _jspx_eval_portlet_namespace_18 = _jspx_th_portlet_namespace_18.doStartTag();
    if (_jspx_th_portlet_namespace_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_18);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_18);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_19(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_25, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_19 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_19.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_25);
    int _jspx_eval_portlet_namespace_19 = _jspx_th_portlet_namespace_19.doStartTag();
    if (_jspx_th_portlet_namespace_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_19);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_19);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_20(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_25, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_20 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_20.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_25);
    int _jspx_eval_portlet_namespace_20 = _jspx_th_portlet_namespace_20.doStartTag();
    if (_jspx_th_portlet_namespace_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_20);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_20);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_21(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_29, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_21 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_21.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_29);
    int _jspx_eval_portlet_namespace_21 = _jspx_th_portlet_namespace_21.doStartTag();
    if (_jspx_th_portlet_namespace_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_21);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_21);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_22(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_29, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_22 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_22.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_29);
    int _jspx_eval_portlet_namespace_22 = _jspx_th_portlet_namespace_22.doStartTag();
    if (_jspx_th_portlet_namespace_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_22);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_22);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_23(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_30, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_23 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_23.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_30);
    int _jspx_eval_portlet_namespace_23 = _jspx_th_portlet_namespace_23.doStartTag();
    if (_jspx_th_portlet_namespace_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_23);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_23);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_24(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_30, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_24 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_24.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_30);
    int _jspx_eval_portlet_namespace_24 = _jspx_th_portlet_namespace_24.doStartTag();
    if (_jspx_th_portlet_namespace_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_24);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_24);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_25(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_34, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_25 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_25.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_25.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_34);
    int _jspx_eval_portlet_namespace_25 = _jspx_th_portlet_namespace_25.doStartTag();
    if (_jspx_th_portlet_namespace_25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_25);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_25);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_26(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_34, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_26 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_26.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_26.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_34);
    int _jspx_eval_portlet_namespace_26 = _jspx_th_portlet_namespace_26.doStartTag();
    if (_jspx_th_portlet_namespace_26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_26);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_26);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_27(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_35, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_27 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_27.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_27.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_35);
    int _jspx_eval_portlet_namespace_27 = _jspx_th_portlet_namespace_27.doStartTag();
    if (_jspx_th_portlet_namespace_27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_27);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_27);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_28(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_35, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_28 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_28.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_28.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_35);
    int _jspx_eval_portlet_namespace_28 = _jspx_th_portlet_namespace_28.doStartTag();
    if (_jspx_th_portlet_namespace_28.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_28);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_28);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_29(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_39, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_29 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_29.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_29.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_39);
    int _jspx_eval_portlet_namespace_29 = _jspx_th_portlet_namespace_29.doStartTag();
    if (_jspx_th_portlet_namespace_29.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_29);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_29);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_30(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_39, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_30 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_30.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_30.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_39);
    int _jspx_eval_portlet_namespace_30 = _jspx_th_portlet_namespace_30.doStartTag();
    if (_jspx_th_portlet_namespace_30.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_30);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_30);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_31(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_40, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_31 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_31.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_31.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_40);
    int _jspx_eval_portlet_namespace_31 = _jspx_th_portlet_namespace_31.doStartTag();
    if (_jspx_th_portlet_namespace_31.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_31);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_31);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_32(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_40, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_32 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_32.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_32.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_40);
    int _jspx_eval_portlet_namespace_32 = _jspx_th_portlet_namespace_32.doStartTag();
    if (_jspx_th_portlet_namespace_32.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_32);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_32);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_33(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_42, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_33 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_33.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_33.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_42);
    int _jspx_eval_portlet_namespace_33 = _jspx_th_portlet_namespace_33.doStartTag();
    if (_jspx_th_portlet_namespace_33.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_33);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_33);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_34(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_42, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_34 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_34.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_34.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_42);
    int _jspx_eval_portlet_namespace_34 = _jspx_th_portlet_namespace_34.doStartTag();
    if (_jspx_th_portlet_namespace_34.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_34);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_34);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_35(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_35 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_35.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_35.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
    int _jspx_eval_portlet_namespace_35 = _jspx_th_portlet_namespace_35.doStartTag();
    if (_jspx_th_portlet_namespace_35.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_35);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_35);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_36(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_36 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_36.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_36.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
    int _jspx_eval_portlet_namespace_36 = _jspx_th_portlet_namespace_36.doStartTag();
    if (_jspx_th_portlet_namespace_36.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_36);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_36);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_37(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_37 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_37.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_37.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
    int _jspx_eval_portlet_namespace_37 = _jspx_th_portlet_namespace_37.doStartTag();
    if (_jspx_th_portlet_namespace_37.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_37);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_37);
    return false;
  }
}
