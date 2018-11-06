package org.apache.jsp.partials;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.frontend.taglib.clay.sample.web.constants.ClaySamplePortletKeys;
import com.liferay.frontend.taglib.clay.sample.web.internal.display.context.CardsDisplayContext;
import com.liferay.frontend.taglib.clay.sample.web.internal.display.context.DropdownsDisplayContext;
import com.liferay.frontend.taglib.clay.sample.web.internal.display.context.ManagementToolbarsDisplayContext;
import com.liferay.frontend.taglib.clay.sample.web.internal.display.context.NavigationBarsDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.SelectOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class management_005ftoolbars_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingOrder_selectable_searchValue_searchInputName_searchFormName_searchActionURL_itemsTotal_filterLabelItems_filterDropdownItems_creationMenu_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingOrder_selectable_searchInputName_searchFormName_searchActionURL_filterDropdownItems_creationMenu_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_management$1toolbar_selectedItems_selectable_itemsTotal_actionDropdownItems_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingOrder_selectable_searchValue_searchInputName_searchFormName_searchActionURL_itemsTotal_filterLabelItems_filterDropdownItems_creationMenu_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingOrder_selectable_searchInputName_searchFormName_searchActionURL_filterDropdownItems_creationMenu_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_management$1toolbar_selectedItems_selectable_itemsTotal_actionDropdownItems_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingOrder_selectable_searchValue_searchInputName_searchFormName_searchActionURL_itemsTotal_filterLabelItems_filterDropdownItems_creationMenu_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingOrder_selectable_searchInputName_searchFormName_searchActionURL_filterDropdownItems_creationMenu_nobody.release();
    _jspx_tagPool_clay_management$1toolbar_selectedItems_selectable_itemsTotal_actionDropdownItems_nobody.release();
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
      out.write("\n\n\n\n\n\n\n\n");
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

CardsDisplayContext cardsDisplayContext = (CardsDisplayContext)request.getAttribute(ClaySamplePortletKeys.CARDS_DISPLAY_CONTEXT);
DropdownsDisplayContext dropdownsDisplayContext = (DropdownsDisplayContext)request.getAttribute(ClaySamplePortletKeys.DROPDOWNS_DISPLAY_CONTEXT);
ManagementToolbarsDisplayContext managementToolbarsDisplayContext = (ManagementToolbarsDisplayContext)request.getAttribute(ClaySamplePortletKeys.MANAGEMENT_TOOLBARS_DISPLAY_CONTEXT);
NavigationBarsDisplayContext navigationBarsDisplayContext = (NavigationBarsDisplayContext)request.getAttribute(ClaySamplePortletKeys.NAVIGATION_BARS_DISPLAY_CONTEXT);

      out.write("\n\n<blockquote><p>Management toolbar is an extension of Toolbar. A combination of different components as filters, orders, search, visualization select and other actions, that allow to manage dataset.</p></blockquote>\n\n<h3>DEFAULT STATE</h3>\n\n");
      //  clay:management-toolbar
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag _jspx_th_clay_management$1toolbar_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag) _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingOrder_selectable_searchInputName_searchFormName_searchActionURL_filterDropdownItems_creationMenu_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag.class);
      _jspx_th_clay_management$1toolbar_0.setPageContext(_jspx_page_context);
      _jspx_th_clay_management$1toolbar_0.setParent(null);
      _jspx_th_clay_management$1toolbar_0.setCreationMenu( managementToolbarsDisplayContext.getCreationMenu() );
      _jspx_th_clay_management$1toolbar_0.setFilterDropdownItems( managementToolbarsDisplayContext.getFilterDropdownItems() );
      _jspx_th_clay_management$1toolbar_0.setSearchActionURL("mySearchActionURL?key1=val1&key2=val2&key3=val3");
      _jspx_th_clay_management$1toolbar_0.setSearchFormName("mySearchName");
      _jspx_th_clay_management$1toolbar_0.setSearchInputName("mySearchInputName");
      _jspx_th_clay_management$1toolbar_0.setSelectable( true );
      _jspx_th_clay_management$1toolbar_0.setSortingOrder("desc");
      _jspx_th_clay_management$1toolbar_0.setViewTypeItems( managementToolbarsDisplayContext.getViewTypeItems() );
      int _jspx_eval_clay_management$1toolbar_0 = _jspx_th_clay_management$1toolbar_0.doStartTag();
      if (_jspx_th_clay_management$1toolbar_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingOrder_selectable_searchInputName_searchFormName_searchActionURL_filterDropdownItems_creationMenu_nobody.reuse(_jspx_th_clay_management$1toolbar_0);
        return;
      }
      _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingOrder_selectable_searchInputName_searchFormName_searchActionURL_filterDropdownItems_creationMenu_nobody.reuse(_jspx_th_clay_management$1toolbar_0);
      out.write("\n\n<h3>ACTIVE STATE</h3>\n\n");
      //  clay:management-toolbar
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag _jspx_th_clay_management$1toolbar_1 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag) _jspx_tagPool_clay_management$1toolbar_selectedItems_selectable_itemsTotal_actionDropdownItems_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag.class);
      _jspx_th_clay_management$1toolbar_1.setPageContext(_jspx_page_context);
      _jspx_th_clay_management$1toolbar_1.setParent(null);
      _jspx_th_clay_management$1toolbar_1.setActionDropdownItems( managementToolbarsDisplayContext.getActionDropdownItems() );
      _jspx_th_clay_management$1toolbar_1.setItemsTotal( 42 );
      _jspx_th_clay_management$1toolbar_1.setSelectable( true );
      _jspx_th_clay_management$1toolbar_1.setSelectedItems( 14 );
      int _jspx_eval_clay_management$1toolbar_1 = _jspx_th_clay_management$1toolbar_1.doStartTag();
      if (_jspx_th_clay_management$1toolbar_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_management$1toolbar_selectedItems_selectable_itemsTotal_actionDropdownItems_nobody.reuse(_jspx_th_clay_management$1toolbar_1);
        return;
      }
      _jspx_tagPool_clay_management$1toolbar_selectedItems_selectable_itemsTotal_actionDropdownItems_nobody.reuse(_jspx_th_clay_management$1toolbar_1);
      out.write("\n\n<h3>WITH RESULTS BAR</h3>\n\n");
      //  clay:management-toolbar
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag _jspx_th_clay_management$1toolbar_2 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag) _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingOrder_selectable_searchValue_searchInputName_searchFormName_searchActionURL_itemsTotal_filterLabelItems_filterDropdownItems_creationMenu_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.ManagementToolbarTag.class);
      _jspx_th_clay_management$1toolbar_2.setPageContext(_jspx_page_context);
      _jspx_th_clay_management$1toolbar_2.setParent(null);
      _jspx_th_clay_management$1toolbar_2.setCreationMenu( managementToolbarsDisplayContext.getCreationMenu() );
      _jspx_th_clay_management$1toolbar_2.setFilterDropdownItems( managementToolbarsDisplayContext.getFilterDropdownItems() );
      _jspx_th_clay_management$1toolbar_2.setFilterLabelItems( managementToolbarsDisplayContext.getFilterLabelItems() );
      _jspx_th_clay_management$1toolbar_2.setItemsTotal( 42 );
      _jspx_th_clay_management$1toolbar_2.setSearchActionURL("mySearchActionURL?key1=val1&key2=val2&key3=val3");
      _jspx_th_clay_management$1toolbar_2.setSearchFormName("mySearchName");
      _jspx_th_clay_management$1toolbar_2.setSearchInputName("mySearchInputName");
      _jspx_th_clay_management$1toolbar_2.setSearchValue("my search");
      _jspx_th_clay_management$1toolbar_2.setSelectable( true );
      _jspx_th_clay_management$1toolbar_2.setSortingOrder("desc");
      _jspx_th_clay_management$1toolbar_2.setViewTypeItems( managementToolbarsDisplayContext.getViewTypeItems() );
      int _jspx_eval_clay_management$1toolbar_2 = _jspx_th_clay_management$1toolbar_2.doStartTag();
      if (_jspx_th_clay_management$1toolbar_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingOrder_selectable_searchValue_searchInputName_searchFormName_searchActionURL_itemsTotal_filterLabelItems_filterDropdownItems_creationMenu_nobody.reuse(_jspx_th_clay_management$1toolbar_2);
        return;
      }
      _jspx_tagPool_clay_management$1toolbar_viewTypeItems_sortingOrder_selectable_searchValue_searchInputName_searchFormName_searchActionURL_itemsTotal_filterLabelItems_filterDropdownItems_creationMenu_nobody.reuse(_jspx_th_clay_management$1toolbar_2);
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
}
