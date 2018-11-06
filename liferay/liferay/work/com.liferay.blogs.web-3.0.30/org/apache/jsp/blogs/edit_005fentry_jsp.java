package org.apache.jsp.blogs;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.asset.constants.AssetWebKeys;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryServiceUtil;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.asset.util.AssetHelper;
import com.liferay.blogs.configuration.BlogsGroupServiceOverriddenConfiguration;
import com.liferay.blogs.constants.BlogsConstants;
import com.liferay.blogs.constants.BlogsPortletKeys;
import com.liferay.blogs.exception.EntryContentException;
import com.liferay.blogs.exception.EntryCoverImageCropException;
import com.liferay.blogs.exception.EntryDescriptionException;
import com.liferay.blogs.exception.EntrySmallImageNameException;
import com.liferay.blogs.exception.EntrySmallImageScaleException;
import com.liferay.blogs.exception.EntryTitleException;
import com.liferay.blogs.exception.EntryUrlTitleException;
import com.liferay.blogs.exception.NoSuchEntryException;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.blogs.service.BlogsEntryServiceUtil;
import com.liferay.blogs.settings.BlogsGroupServiceSettings;
import com.liferay.blogs.util.comparator.EntryModifiedDateComparator;
import com.liferay.blogs.web.constants.BlogsWebKeys;
import com.liferay.blogs.web.internal.BlogsItemSelectorHelper;
import com.liferay.blogs.web.internal.configuration.BlogsPortletInstanceConfiguration;
import com.liferay.blogs.web.internal.display.context.BlogEntriesDisplayContext;
import com.liferay.blogs.web.internal.display.context.BlogEntriesManagementToolbarDisplayContext;
import com.liferay.blogs.web.internal.display.context.BlogImagesDisplayContext;
import com.liferay.blogs.web.internal.display.context.BlogImagesManagementToolbarDisplayContext;
import com.liferay.blogs.web.internal.display.context.BlogsPortletInstanceSettingsHelper;
import com.liferay.blogs.web.internal.security.permission.resource.BlogsEntryPermission;
import com.liferay.blogs.web.internal.security.permission.resource.BlogsPermission;
import com.liferay.blogs.web.internal.social.SocialBookmarksUtil;
import com.liferay.blogs.web.internal.util.BlogsEntryUtil;
import com.liferay.blogs.web.internal.util.BlogsUtil;
import com.liferay.document.library.display.context.DLMimeTypeDisplayContext;
import com.liferay.document.library.kernel.exception.FileSizeException;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.friendly.url.exception.DuplicateFriendlyURLEntryException;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPNavigationItemList;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.comment.CommentManagerUtil;
import com.liferay.portal.kernel.comment.Discussion;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.dao.search.SearchContainerResults;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContextFunction;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.settings.ParameterMapSettingsLocator;
import com.liferay.portal.kernel.settings.PortletInstanceSettingsLocator;
import com.liferay.portal.kernel.upload.LiferayFileItemException;
import com.liferay.portal.kernel.upload.UploadRequestSizeException;
import com.liferay.portal.kernel.upload.UploadServletRequestConfigurationHelperUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.util.UnicodeFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.upload.LiferayFileItem;
import com.liferay.portal.util.PropsValues;
import com.liferay.ratings.kernel.model.RatingsEntry;
import com.liferay.ratings.kernel.model.RatingsStats;
import com.liferay.ratings.kernel.service.RatingsEntryLocalServiceUtil;
import com.liferay.ratings.kernel.service.RatingsStatsLocalServiceUtil;
import com.liferay.rss.util.RSSUtil;
import com.liferay.taglib.search.ResultRow;
import java.text.Format;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import com.liferay.document.library.kernel.util.DLValidatorUtil;

public final class edit_005fentry_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(4);
    _jspx_dependants.add("/blogs/init.jsp");
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
    _jspx_dependants.add("/blogs/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_error_exception;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_model$1context_model_bean_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1item$1selector_image$1selector_validExtensions_uploadURL_paramName_maxFileSize_itemSelectorURL_itemSelectorEventName_fileEntryId_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_value_type_name_disabled_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_type_name_href_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_title_name_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1expando_custom$1attributes$1available_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1asset_input$1asset$1links_classPK_className_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button$1row_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset$1group_markupView;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_buffer_var;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_validator_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset_label_collapsible_collapsed;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1expando_custom$1attribute$1list_label_editable_classPK_className_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_label_disabled;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_actionURL_windowState_var_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_form_onSubmit_name_method_enctype_cssClass_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1reading$1time_reading$1time_model_id_displayStyle_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_actionURL_var_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1item$1selector_image$1selector_validExtensions_uploadURL_paramName_maxFileSize_itemSelectorURL_itemSelectorEventName_fileEntryId_draggableImage_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_label_helpMessage_checked_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon$1help_message_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_input$1permissions_modelName_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1asset_asset$1categories$1error_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_showHelpMessage_markupView_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_label_checked_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_field$1wrapper_name_label_helpMessage_disabled_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_prefix_name_label_ignoreRequestValue_disabled_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_use;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1asset_asset$1tags$1error_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_input$1editor_showSource_placeholder_name_editorName_contents_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_value_type_primary_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_input$1editor_required_placeholder_onChangeMethod_name_editorName_contents;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1asset_asset$1tags$1selector_classPK_className_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_info$1bar_fixed;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_name_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_error_message_exception_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_input$1editor_showSource_placeholder_onChangeMethod_name_editorName_contents_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1trash_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1asset_asset$1categories$1selector_classPK_className_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_error_exception = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_model$1context_model_bean_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1item$1selector_image$1selector_validExtensions_uploadURL_paramName_maxFileSize_itemSelectorURL_itemSelectorEventName_fileEntryId_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_value_type_name_disabled_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_type_name_href_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_title_name_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1expando_custom$1attributes$1available_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1asset_input$1asset$1links_classPK_className_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button$1row_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset$1group_markupView = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_buffer_var = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_validator_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset_label_collapsible_collapsed = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1expando_custom$1attribute$1list_label_editable_classPK_className_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_label_disabled = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_actionURL_windowState_var_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_form_onSubmit_name_method_enctype_cssClass_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1reading$1time_reading$1time_model_id_displayStyle_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_actionURL_var_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1item$1selector_image$1selector_validExtensions_uploadURL_paramName_maxFileSize_itemSelectorURL_itemSelectorEventName_fileEntryId_draggableImage_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_label_helpMessage_checked_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon$1help_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_input$1permissions_modelName_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1asset_asset$1categories$1error_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_showHelpMessage_markupView_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_label_checked_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_field$1wrapper_name_label_helpMessage_disabled_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_prefix_name_label_ignoreRequestValue_disabled_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_use = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1asset_asset$1tags$1error_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_input$1editor_showSource_placeholder_name_editorName_contents_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_value_type_primary_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_input$1editor_required_placeholder_onChangeMethod_name_editorName_contents = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1asset_asset$1tags$1selector_classPK_className_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_info$1bar_fixed = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_name_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_error_message_exception_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_input$1editor_showSource_placeholder_onChangeMethod_name_editorName_contents_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1trash_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1asset_asset$1categories$1selector_classPK_className_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody.release();
    _jspx_tagPool_liferay$1ui_error_exception.release();
    _jspx_tagPool_aui_model$1context_model_bean_nobody.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_liferay$1item$1selector_image$1selector_validExtensions_uploadURL_paramName_maxFileSize_itemSelectorURL_itemSelectorEventName_fileEntryId_nobody.release();
    _jspx_tagPool_aui_button_value_type_name_disabled_nobody.release();
    _jspx_tagPool_aui_button_type_name_href_nobody.release();
    _jspx_tagPool_aui_input_value_type_title_name_label_nobody.release();
    _jspx_tagPool_liferay$1expando_custom$1attributes$1available_className.release();
    _jspx_tagPool_liferay$1asset_input$1asset$1links_classPK_className_nobody.release();
    _jspx_tagPool_aui_button$1row_cssClass.release();
    _jspx_tagPool_aui_input_type_name_nobody.release();
    _jspx_tagPool_aui_fieldset$1group_markupView.release();
    _jspx_tagPool_liferay$1util_buffer_var.release();
    _jspx_tagPool_aui_fieldset.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_aui_validator_name_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1expando_custom$1attribute$1list_label_editable_classPK_className_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_label_disabled.release();
    _jspx_tagPool_portlet_actionURL_windowState_var_name.release();
    _jspx_tagPool_aui_form_onSubmit_name_method_enctype_cssClass_action.release();
    _jspx_tagPool_liferay$1reading$1time_reading$1time_model_id_displayStyle_nobody.release();
    _jspx_tagPool_portlet_actionURL_var_name_nobody.release();
    _jspx_tagPool_liferay$1item$1selector_image$1selector_validExtensions_uploadURL_paramName_maxFileSize_itemSelectorURL_itemSelectorEventName_fileEntryId_draggableImage_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_label_helpMessage_checked_nobody.release();
    _jspx_tagPool_liferay$1ui_icon$1help_message_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1ui_input$1permissions_modelName_nobody.release();
    _jspx_tagPool_liferay$1asset_asset$1categories$1error_nobody.release();
    _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_showHelpMessage_markupView_nobody.release();
    _jspx_tagPool_aui_script.release();
    _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.release();
    _jspx_tagPool_aui_field$1wrapper_name_label_helpMessage_disabled_cssClass.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_aui_input_value_type_prefix_name_label_ignoreRequestValue_disabled_cssClass_nobody.release();
    _jspx_tagPool_aui_script_use.release();
    _jspx_tagPool_liferay$1asset_asset$1tags$1error_nobody.release();
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_liferay$1ui_input$1editor_showSource_placeholder_name_editorName_contents_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_label_nobody.release();
    _jspx_tagPool_aui_button_value_type_primary_name_nobody.release();
    _jspx_tagPool_liferay$1ui_input$1editor_required_placeholder_onChangeMethod_name_editorName_contents.release();
    _jspx_tagPool_liferay$1asset_asset$1tags$1selector_classPK_className_nobody.release();
    _jspx_tagPool_aui_input_value_type_name_nobody.release();
    _jspx_tagPool_liferay$1frontend_info$1bar_fixed.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_aui_input_name_label_nobody.release();
    _jspx_tagPool_liferay$1ui_error_message_exception_nobody.release();
    _jspx_tagPool_liferay$1ui_input$1editor_showSource_placeholder_onChangeMethod_name_editorName_contents_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.release();
    _jspx_tagPool_liferay$1trash_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1asset_asset$1categories$1selector_classPK_className_nobody.release();
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
      //  liferay-trash:defineObjects
      com.liferay.trash.taglib.servlet.taglib.DefineObjectsTag _jspx_th_liferay$1trash_defineObjects_0 = (com.liferay.trash.taglib.servlet.taglib.DefineObjectsTag) _jspx_tagPool_liferay$1trash_defineObjects_nobody.get(com.liferay.trash.taglib.servlet.taglib.DefineObjectsTag.class);
      _jspx_th_liferay$1trash_defineObjects_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1trash_defineObjects_0.setParent(null);
      int _jspx_eval_liferay$1trash_defineObjects_0 = _jspx_th_liferay$1trash_defineObjects_0.doStartTag();
      if (_jspx_th_liferay$1trash_defineObjects_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1trash_defineObjects_nobody.reuse(_jspx_th_liferay$1trash_defineObjects_0);
        return;
      }
      _jspx_tagPool_liferay$1trash_defineObjects_nobody.reuse(_jspx_th_liferay$1trash_defineObjects_0);
      com.liferay.trash.TrashHelper trashHelper = null;
      trashHelper = (com.liferay.trash.TrashHelper) _jspx_page_context.findAttribute("trashHelper");
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

AssetHelper assetHelper = (AssetHelper)request.getAttribute(AssetWebKeys.ASSET_HELPER);

Format dateFormatDate = FastDateFormatFactoryUtil.getDate(locale, timeZone);

      out.write('\n');
      out.write('\n');
      out.write("\n\n\n\n");

String portletResource = ParamUtil.getString(request, "portletResource");

String portletId = portletDisplay.getId();

if (Validator.isNotNull(portletResource)) {
	portletId = portletResource;
	portletName = portletResource;
}

BlogsGroupServiceSettings blogsGroupServiceSettings = BlogsGroupServiceSettings.getInstance(scopeGroupId);
BlogsPortletInstanceConfiguration blogsPortletInstanceConfiguration = ConfigurationProviderUtil.getConfiguration(BlogsPortletInstanceConfiguration.class, new PortletInstanceSettingsLocator(themeDisplay.getLayout(), portletDisplay.getId()));

BlogsPortletInstanceSettingsHelper blogsPortletInstanceSettingsHelper = new BlogsPortletInstanceSettingsHelper(request, blogsPortletInstanceConfiguration);

int pageAbstractLength = PropsValues.BLOGS_PAGE_ABSTRACT_LENGTH;

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

String redirect = ParamUtil.getString(request, "redirect");

String referringPortletResource = ParamUtil.getString(request, "referringPortletResource");

BlogsEntry entry = (BlogsEntry)request.getAttribute(WebKeys.BLOGS_ENTRY);

long entryId = BeanParamUtil.getLong(entry, request, "entryId");

String title = BeanParamUtil.getString(entry, request, "title");
String subtitle = BeanParamUtil.getString(entry, request, "subtitle");
String content = BeanParamUtil.getString(entry, request, "content");
String urlTitle = BeanParamUtil.getString(entry, request, "urlTitle");

String description = BeanParamUtil.getString(entry, request, "description");

boolean customAbstract = ParamUtil.getBoolean(request, "customAbstract", (entry != null) && Validator.isNotNull(entry.getDescription()) ? true : false);

if (!customAbstract) {
	description = StringUtil.shorten(content, pageAbstractLength);
}

boolean allowPingbacks = PropsValues.BLOGS_PINGBACK_ENABLED && BeanParamUtil.getBoolean(entry, request, "allowPingbacks", true);
boolean allowTrackbacks = PropsValues.BLOGS_TRACKBACK_ENABLED && BeanParamUtil.getBoolean(entry, request, "allowTrackbacks", true);
String coverImageCaption = BeanParamUtil.getString(entry, request, "coverImageCaption");
long coverImageFileEntryId = BeanParamUtil.getLong(entry, request, "coverImageFileEntryId");
long smallImageFileEntryId = BeanParamUtil.getLong(entry, request, "smallImageFileEntryId");

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

boolean portletTitleBasedNavigation = GetterUtil.getBoolean(portletConfig.getInitParameter("portlet-title-based-navigation"));

if (portletTitleBasedNavigation) {
	renderResponse.setTitle((entry != null) ? BlogsEntryUtil.getDisplayTitle(resourceBundle, entry) : LanguageUtil.get(request, "new-blog-entry"));
}

      out.write('\n');
      out.write('\n');
      //  liferay-util:buffer
      com.liferay.taglib.util.BufferTag _jspx_th_liferay$1util_buffer_0 = (com.liferay.taglib.util.BufferTag) _jspx_tagPool_liferay$1util_buffer_var.get(com.liferay.taglib.util.BufferTag.class);
      _jspx_th_liferay$1util_buffer_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1util_buffer_0.setParent(null);
      _jspx_th_liferay$1util_buffer_0.setVar("saveStatus");
      int _jspx_eval_liferay$1util_buffer_0 = _jspx_th_liferay$1util_buffer_0.doStartTag();
      if (_jspx_eval_liferay$1util_buffer_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_liferay$1util_buffer_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_liferay$1util_buffer_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_liferay$1util_buffer_0.doInitBody();
        }
        do {
          out.write('\n');
          out.write('	');
          //  c:choose
          com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
          _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
          _jspx_th_c_choose_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_buffer_0);
          int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
          if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            //  c:when
            com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
            _jspx_th_c_when_0.setPageContext(_jspx_page_context);
            _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
            _jspx_th_c_when_0.setTest( entry != null );
            int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
            if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t<small class=\"text-capitalize text-muted\" id=\"");
              if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_0, _jspx_page_context))
                return;
              out.write("saveStatus\">\n\t\t\t\t");
              //  aui:workflow-status
              com.liferay.taglib.aui.WorkflowStatusTag _jspx_th_aui_workflow$1status_0 = (com.liferay.taglib.aui.WorkflowStatusTag) _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_showHelpMessage_markupView_nobody.get(com.liferay.taglib.aui.WorkflowStatusTag.class);
              _jspx_th_aui_workflow$1status_0.setPageContext(_jspx_page_context);
              _jspx_th_aui_workflow$1status_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
              _jspx_th_aui_workflow$1status_0.setMarkupView("lexicon");
              _jspx_th_aui_workflow$1status_0.setShowHelpMessage( false );
              _jspx_th_aui_workflow$1status_0.setShowIcon( false );
              _jspx_th_aui_workflow$1status_0.setShowLabel( false );
              _jspx_th_aui_workflow$1status_0.setStatus( entry.getStatus() );
              int _jspx_eval_aui_workflow$1status_0 = _jspx_th_aui_workflow$1status_0.doStartTag();
              if (_jspx_th_aui_workflow$1status_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_showHelpMessage_markupView_nobody.reuse(_jspx_th_aui_workflow$1status_0);
                return;
              }
              _jspx_tagPool_aui_workflow$1status_status_showLabel_showIcon_showHelpMessage_markupView_nobody.reuse(_jspx_th_aui_workflow$1status_0);
              out.write("\n\n\t\t\t\t");
              //  liferay-ui:message
              com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
              _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
              _jspx_th_liferay$1ui_message_0.setArguments( LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - entry.getStatusDate().getTime(), true) );
              _jspx_th_liferay$1ui_message_0.setKey("x-ago");
              _jspx_th_liferay$1ui_message_0.setTranslateArguments( false );
              int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
              if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_0);
                return;
              }
              _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_0);
              out.write("\n\t\t\t</small>\n\t\t");
            }
            if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
              return;
            }
            _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
            out.write("\n\t\t");
            if (_jspx_meth_c_otherwise_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_choose_0, _jspx_page_context))
              return;
            out.write('\n');
            out.write('	');
          }
          if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
            return;
          }
          _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
          out.write('\n');
          int evalDoAfterBody = _jspx_th_liferay$1util_buffer_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_liferay$1util_buffer_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_liferay$1util_buffer_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1util_buffer_var.reuse(_jspx_th_liferay$1util_buffer_0);
        return;
      }
      _jspx_tagPool_liferay$1util_buffer_var.reuse(_jspx_th_liferay$1util_buffer_0);
      java.lang.String saveStatus = null;
      saveStatus = (java.lang.String) _jspx_page_context.findAttribute("saveStatus");
      out.write('\n');
      out.write('\n');
      //  liferay-util:buffer
      com.liferay.taglib.util.BufferTag _jspx_th_liferay$1util_buffer_1 = (com.liferay.taglib.util.BufferTag) _jspx_tagPool_liferay$1util_buffer_var.get(com.liferay.taglib.util.BufferTag.class);
      _jspx_th_liferay$1util_buffer_1.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1util_buffer_1.setParent(null);
      _jspx_th_liferay$1util_buffer_1.setVar("readingTime");
      int _jspx_eval_liferay$1util_buffer_1 = _jspx_th_liferay$1util_buffer_1.doStartTag();
      if (_jspx_eval_liferay$1util_buffer_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_liferay$1util_buffer_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_liferay$1util_buffer_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_liferay$1util_buffer_1.doInitBody();
        }
        do {
          out.write('\n');
          out.write('	');
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_0.setPageContext(_jspx_page_context);
          _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_buffer_1);
          _jspx_th_c_if_0.setTest( blogsPortletInstanceConfiguration.enableReadingTime() );
          int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
          if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t<small class=\"reading-time-wrapper text-muted\">\n\t\t\t");
            //  liferay-reading-time:reading-time
            com.liferay.reading.time.taglib.servlet.taglib.ReadingTimeTag _jspx_th_liferay$1reading$1time_reading$1time_0 = (com.liferay.reading.time.taglib.servlet.taglib.ReadingTimeTag) _jspx_tagPool_liferay$1reading$1time_reading$1time_model_id_displayStyle_nobody.get(com.liferay.reading.time.taglib.servlet.taglib.ReadingTimeTag.class);
            _jspx_th_liferay$1reading$1time_reading$1time_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1reading$1time_reading$1time_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
            _jspx_th_liferay$1reading$1time_reading$1time_0.setDisplayStyle("descriptive");
            _jspx_th_liferay$1reading$1time_reading$1time_0.setId("readingTime");
            _jspx_th_liferay$1reading$1time_reading$1time_0.setModel( entry );
            int _jspx_eval_liferay$1reading$1time_reading$1time_0 = _jspx_th_liferay$1reading$1time_reading$1time_0.doStartTag();
            if (_jspx_th_liferay$1reading$1time_reading$1time_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1reading$1time_reading$1time_model_id_displayStyle_nobody.reuse(_jspx_th_liferay$1reading$1time_reading$1time_0);
              return;
            }
            _jspx_tagPool_liferay$1reading$1time_reading$1time_model_id_displayStyle_nobody.reuse(_jspx_th_liferay$1reading$1time_reading$1time_0);
            out.write("\n\t\t</small>\n\t");
          }
          if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
          out.write('\n');
          int evalDoAfterBody = _jspx_th_liferay$1util_buffer_1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_liferay$1util_buffer_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_liferay$1util_buffer_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1util_buffer_var.reuse(_jspx_th_liferay$1util_buffer_1);
        return;
      }
      _jspx_tagPool_liferay$1util_buffer_var.reuse(_jspx_th_liferay$1util_buffer_1);
      java.lang.String readingTime = null;
      readingTime = (java.lang.String) _jspx_page_context.findAttribute("readingTime");
      out.write('\n');
      out.write('\n');
      //  c:if
      com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
      _jspx_th_c_if_1.setPageContext(_jspx_page_context);
      _jspx_th_c_if_1.setParent(null);
      _jspx_th_c_if_1.setTest( portletTitleBasedNavigation );
      int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
      if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        //  liferay-frontend:info-bar
        com.liferay.frontend.taglib.servlet.taglib.InfoBarTag _jspx_th_liferay$1frontend_info$1bar_0 = (com.liferay.frontend.taglib.servlet.taglib.InfoBarTag) _jspx_tagPool_liferay$1frontend_info$1bar_fixed.get(com.liferay.frontend.taglib.servlet.taglib.InfoBarTag.class);
        _jspx_th_liferay$1frontend_info$1bar_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1frontend_info$1bar_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
        _jspx_th_liferay$1frontend_info$1bar_0.setFixed( true );
        int _jspx_eval_liferay$1frontend_info$1bar_0 = _jspx_th_liferay$1frontend_info$1bar_0.doStartTag();
        if (_jspx_eval_liferay$1frontend_info$1bar_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          out.print( saveStatus );
          out.write("\n\t\t");
          out.print( readingTime );
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_liferay$1frontend_info$1bar_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1frontend_info$1bar_fixed.reuse(_jspx_th_liferay$1frontend_info$1bar_0);
          return;
        }
        _jspx_tagPool_liferay$1frontend_info$1bar_fixed.reuse(_jspx_th_liferay$1frontend_info$1bar_0);
        out.write('\n');
      }
      if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
      out.write('\n');
      out.write('\n');
      //  portlet:actionURL
      com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_0 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name_nobody.get(com.liferay.taglib.portlet.ActionURLTag.class);
      _jspx_th_portlet_actionURL_0.setPageContext(_jspx_page_context);
      _jspx_th_portlet_actionURL_0.setParent(null);
      _jspx_th_portlet_actionURL_0.setName("/blogs/edit_entry");
      _jspx_th_portlet_actionURL_0.setVar("editEntryURL");
      int _jspx_eval_portlet_actionURL_0 = _jspx_th_portlet_actionURL_0.doStartTag();
      if (_jspx_th_portlet_actionURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_portlet_actionURL_var_name_nobody.reuse(_jspx_th_portlet_actionURL_0);
        return;
      }
      _jspx_tagPool_portlet_actionURL_var_name_nobody.reuse(_jspx_th_portlet_actionURL_0);
      java.lang.String editEntryURL = null;
      editEntryURL = (java.lang.String) _jspx_page_context.findAttribute("editEntryURL");
      out.write("\n\n<div class=\"container-fluid-1280 entry-body\">\n\t");
      //  aui:form
      com.liferay.taglib.aui.FormTag _jspx_th_aui_form_0 = (com.liferay.taglib.aui.FormTag) _jspx_tagPool_aui_form_onSubmit_name_method_enctype_cssClass_action.get(com.liferay.taglib.aui.FormTag.class);
      _jspx_th_aui_form_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_form_0.setParent(null);
      _jspx_th_aui_form_0.setAction( editEntryURL );
      _jspx_th_aui_form_0.setCssClass("edit-entry");
      _jspx_th_aui_form_0.setDynamicAttribute(null, "enctype", new String("multipart/form-data"));
      _jspx_th_aui_form_0.setMethod("post");
      _jspx_th_aui_form_0.setName("fm");
      _jspx_th_aui_form_0.setOnSubmit("event.preventDefault();");
      int _jspx_eval_aui_form_0 = _jspx_th_aui_form_0.doStartTag();
      if (_jspx_eval_aui_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t");
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_0 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_0.setName( Constants.CMD );
        _jspx_th_aui_input_0.setType("hidden");
        int _jspx_eval_aui_input_0 = _jspx_th_aui_input_0.doStartTag();
        if (_jspx_th_aui_input_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_0);
          return;
        }
        _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_0);
        out.write("\n\t\t");
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_1 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_1.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_1.setName("redirect");
        _jspx_th_aui_input_1.setType("hidden");
        _jspx_th_aui_input_1.setValue( redirect );
        int _jspx_eval_aui_input_1 = _jspx_th_aui_input_1.doStartTag();
        if (_jspx_th_aui_input_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_1);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_1);
        out.write("\n\t\t");
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_2 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_2.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_2.setName("referringPortletResource");
        _jspx_th_aui_input_2.setType("hidden");
        _jspx_th_aui_input_2.setValue( referringPortletResource );
        int _jspx_eval_aui_input_2 = _jspx_th_aui_input_2.doStartTag();
        if (_jspx_th_aui_input_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_2);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_2);
        out.write("\n\t\t");
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_3 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_3.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_3.setName("entryId");
        _jspx_th_aui_input_3.setType("hidden");
        _jspx_th_aui_input_3.setValue( entryId );
        int _jspx_eval_aui_input_3 = _jspx_th_aui_input_3.doStartTag();
        if (_jspx_th_aui_input_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_3);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_3);
        out.write("\n\t\t");
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_4 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_4.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_input_4.setName("workflowAction");
        _jspx_th_aui_input_4.setType("hidden");
        _jspx_th_aui_input_4.setValue( WorkflowConstants.ACTION_PUBLISH );
        int _jspx_eval_aui_input_4 = _jspx_th_aui_input_4.doStartTag();
        if (_jspx_th_aui_input_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_4);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_4);
        out.write("\n\n\t\t");
        //  c:if
        com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
        _jspx_th_c_if_2.setPageContext(_jspx_page_context);
        _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_c_if_2.setTest( !portletTitleBasedNavigation );
        int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
        if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t<div class=\"entry-options\">\n\t\t\t\t<div class=\"status\">\n\t\t\t\t\t");
          out.print( saveStatus );
          out.write("\n\t\t\t\t\t");
          out.print( readingTime );
          out.write("\n\t\t\t\t</div>\n\t\t\t</div>\n\t\t");
        }
        if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
          return;
        }
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
        out.write("\n\n\t\t<div class=\"lfr-form-content\">\n\t\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_0 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_0.setException( DuplicateFriendlyURLEntryException.class );
        _jspx_th_liferay$1ui_error_0.setMessage("the-url-title-is-already-in-use-please-enter-a-unique-url-title");
        int _jspx_eval_liferay$1ui_error_0 = _jspx_th_liferay$1ui_error_0.doStartTag();
        if (_jspx_th_liferay$1ui_error_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_0);
        out.write("\n\t\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_1 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_1.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_1.setException( EntryContentException.class );
        _jspx_th_liferay$1ui_error_1.setMessage("please-enter-valid-content");
        int _jspx_eval_liferay$1ui_error_1 = _jspx_th_liferay$1ui_error_1.doStartTag();
        if (_jspx_th_liferay$1ui_error_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_1);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_1);
        out.write("\n\t\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_2 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_2.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_2.setException( EntryCoverImageCropException.class );
        _jspx_th_liferay$1ui_error_2.setMessage("an-error-occurred-while-cropping-the-cover-image");
        int _jspx_eval_liferay$1ui_error_2 = _jspx_th_liferay$1ui_error_2.doStartTag();
        if (_jspx_th_liferay$1ui_error_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_2);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_2);
        out.write("\n\t\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_3 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_3.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_3.setException( EntryDescriptionException.class );
        _jspx_th_liferay$1ui_error_3.setMessage("please-enter-a-valid-abstract");
        int _jspx_eval_liferay$1ui_error_3 = _jspx_th_liferay$1ui_error_3.doStartTag();
        if (_jspx_th_liferay$1ui_error_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_3);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_3);
        out.write("\n\t\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_4 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_4.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_4.setException( EntryTitleException.class );
        _jspx_th_liferay$1ui_error_4.setMessage("please-enter-a-valid-title");
        int _jspx_eval_liferay$1ui_error_4 = _jspx_th_liferay$1ui_error_4.doStartTag();
        if (_jspx_th_liferay$1ui_error_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_4);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_4);
        out.write("\n\t\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_5 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_message_exception_nobody.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_5.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_5.setException( EntryUrlTitleException.class );
        _jspx_th_liferay$1ui_error_5.setMessage("please-enter-a-valid-url-title");
        int _jspx_eval_liferay$1ui_error_5 = _jspx_th_liferay$1ui_error_5.doStartTag();
        if (_jspx_th_liferay$1ui_error_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_5);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_message_exception_nobody.reuse(_jspx_th_liferay$1ui_error_5);
        out.write("\n\n\t\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_6 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_exception.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_6.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_6.setException( LiferayFileItemException.class );
        int _jspx_eval_liferay$1ui_error_6 = _jspx_th_liferay$1ui_error_6.doStartTag();
        if (_jspx_eval_liferay$1ui_error_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          java.lang.Object errorException = null;
          if (_jspx_eval_liferay$1ui_error_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_liferay$1ui_error_6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_liferay$1ui_error_6.doInitBody();
          }
          errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
          do {
            out.write("\n\t\t\t\t");
            //  liferay-ui:message
            com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
            _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_6);
            _jspx_th_liferay$1ui_message_1.setArguments( TextFormatter.formatStorageSize(LiferayFileItem.THRESHOLD_SIZE, locale) );
            _jspx_th_liferay$1ui_message_1.setKey("please-enter-valid-content-with-valid-content-size-no-larger-than-x");
            _jspx_th_liferay$1ui_message_1.setTranslateArguments( false );
            int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
            if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_1);
              return;
            }
            _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_1);
            out.write("\n\t\t\t");
            int evalDoAfterBody = _jspx_th_liferay$1ui_error_6.doAfterBody();
            errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
          if (_jspx_eval_liferay$1ui_error_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
            out = _jspx_page_context.popBody();
        }
        if (_jspx_th_liferay$1ui_error_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_6);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_6);
        out.write("\n\n\t\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_7 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_exception.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_7.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_7.setException( FileSizeException.class );
        int _jspx_eval_liferay$1ui_error_7 = _jspx_th_liferay$1ui_error_7.doStartTag();
        if (_jspx_eval_liferay$1ui_error_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          java.lang.Object errorException = null;
          if (_jspx_eval_liferay$1ui_error_7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_liferay$1ui_error_7.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_liferay$1ui_error_7.doInitBody();
          }
          errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
          do {
            out.write("\n\t\t\t\t");
            //  liferay-ui:message
            com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
            _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_7);
            _jspx_th_liferay$1ui_message_2.setArguments( TextFormatter.formatStorageSize(DLValidatorUtil.getMaxAllowableSize(), locale) );
            _jspx_th_liferay$1ui_message_2.setKey("please-enter-a-file-with-a-valid-file-size-no-larger-than-x");
            _jspx_th_liferay$1ui_message_2.setTranslateArguments( false );
            int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
            if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_2);
              return;
            }
            _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_2);
            out.write("\n\t\t\t");
            int evalDoAfterBody = _jspx_th_liferay$1ui_error_7.doAfterBody();
            errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
          if (_jspx_eval_liferay$1ui_error_7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
            out = _jspx_page_context.popBody();
        }
        if (_jspx_th_liferay$1ui_error_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_7);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_7);
        out.write("\n\n\t\t\t");
        //  liferay-ui:error
        com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_8 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_exception.get(com.liferay.taglib.ui.ErrorTag.class);
        _jspx_th_liferay$1ui_error_8.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_error_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_liferay$1ui_error_8.setException( UploadRequestSizeException.class );
        int _jspx_eval_liferay$1ui_error_8 = _jspx_th_liferay$1ui_error_8.doStartTag();
        if (_jspx_eval_liferay$1ui_error_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          java.lang.Object errorException = null;
          if (_jspx_eval_liferay$1ui_error_8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_liferay$1ui_error_8.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_liferay$1ui_error_8.doInitBody();
          }
          errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
          do {
            out.write("\n\t\t\t\t");
            //  liferay-ui:message
            com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_3 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
            _jspx_th_liferay$1ui_message_3.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_message_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_8);
            _jspx_th_liferay$1ui_message_3.setArguments( TextFormatter.formatStorageSize(UploadServletRequestConfigurationHelperUtil.getMaxSize(), locale) );
            _jspx_th_liferay$1ui_message_3.setKey("request-is-larger-than-x-and-could-not-be-processed");
            _jspx_th_liferay$1ui_message_3.setTranslateArguments( false );
            int _jspx_eval_liferay$1ui_message_3 = _jspx_th_liferay$1ui_message_3.doStartTag();
            if (_jspx_th_liferay$1ui_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_3);
              return;
            }
            _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_3);
            out.write("\n\t\t\t");
            int evalDoAfterBody = _jspx_th_liferay$1ui_error_8.doAfterBody();
            errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
          if (_jspx_eval_liferay$1ui_error_8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
            out = _jspx_page_context.popBody();
        }
        if (_jspx_th_liferay$1ui_error_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_8);
          return;
        }
        _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_8);
        out.write("\n\n\t\t\t");
        if (_jspx_meth_liferay$1asset_asset$1categories$1error_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_form_0, _jspx_page_context))
          return;
        out.write("\n\n\t\t\t");
        if (_jspx_meth_liferay$1asset_asset$1tags$1error_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_form_0, _jspx_page_context))
          return;
        out.write("\n\n\t\t\t");
        //  aui:model-context
        com.liferay.taglib.aui.ModelContextTag _jspx_th_aui_model$1context_0 = (com.liferay.taglib.aui.ModelContextTag) _jspx_tagPool_aui_model$1context_model_bean_nobody.get(com.liferay.taglib.aui.ModelContextTag.class);
        _jspx_th_aui_model$1context_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_model$1context_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_model$1context_0.setBean( entry );
        _jspx_th_aui_model$1context_0.setModel( BlogsEntry.class );
        int _jspx_eval_aui_model$1context_0 = _jspx_th_aui_model$1context_0.doStartTag();
        if (_jspx_th_aui_model$1context_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_model$1context_model_bean_nobody.reuse(_jspx_th_aui_model$1context_0);
          return;
        }
        _jspx_tagPool_aui_model$1context_model_bean_nobody.reuse(_jspx_th_aui_model$1context_0);
        out.write("\n\n\t\t\t");

			BlogsItemSelectorHelper blogsItemSelectorHelper = (BlogsItemSelectorHelper)request.getAttribute(BlogsWebKeys.BLOGS_ITEM_SELECTOR_HELPER);
			String[] imageExtensions = PrefsPropsUtil.getStringArray(PropsKeys.BLOGS_IMAGE_EXTENSIONS, StringPool.COMMA);
			RequestBackedPortletURLFactory requestBackedPortletURLFactory = RequestBackedPortletURLFactoryUtil.create(liferayPortletRequest);
			
        out.write("\n\n\t\t\t");
        //  aui:fieldset-group
        com.liferay.taglib.aui.FieldsetGroupTag _jspx_th_aui_fieldset$1group_0 = (com.liferay.taglib.aui.FieldsetGroupTag) _jspx_tagPool_aui_fieldset$1group_markupView.get(com.liferay.taglib.aui.FieldsetGroupTag.class);
        _jspx_th_aui_fieldset$1group_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_fieldset$1group_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_fieldset$1group_0.setMarkupView("lexicon");
        int _jspx_eval_aui_fieldset$1group_0 = _jspx_th_aui_fieldset$1group_0.doStartTag();
        if (_jspx_eval_aui_fieldset$1group_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t\t");
          //  aui:fieldset
          com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_0 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset.get(com.liferay.taglib.aui.FieldsetTag.class);
          _jspx_th_aui_fieldset_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_fieldset_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset$1group_0);
          int _jspx_eval_aui_fieldset_0 = _jspx_th_aui_fieldset_0.doStartTag();
          if (_jspx_eval_aui_fieldset_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t");
            //  portlet:actionURL
            com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_1 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name_nobody.get(com.liferay.taglib.portlet.ActionURLTag.class);
            _jspx_th_portlet_actionURL_1.setPageContext(_jspx_page_context);
            _jspx_th_portlet_actionURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_portlet_actionURL_1.setName("/blogs/upload_cover_image");
            _jspx_th_portlet_actionURL_1.setVar("uploadCoverImageURL");
            int _jspx_eval_portlet_actionURL_1 = _jspx_th_portlet_actionURL_1.doStartTag();
            if (_jspx_th_portlet_actionURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_actionURL_var_name_nobody.reuse(_jspx_th_portlet_actionURL_1);
              return;
            }
            _jspx_tagPool_portlet_actionURL_var_name_nobody.reuse(_jspx_th_portlet_actionURL_1);
            java.lang.String uploadCoverImageURL = null;
            uploadCoverImageURL = (java.lang.String) _jspx_page_context.findAttribute("uploadCoverImageURL");
            out.write("\n\n\t\t\t\t\t<div class=\"lfr-blogs-cover-image-selector\">\n\n\t\t\t\t\t\t");

						String coverImageSelectedItemEventName = liferayPortletResponse.getNamespace() + "coverImageSelectedItem";
						
            out.write("\n\n\t\t\t\t\t\t");
            //  liferay-item-selector:image-selector
            com.liferay.item.selector.taglib.servlet.taglib.ImageSelectorTag _jspx_th_liferay$1item$1selector_image$1selector_0 = (com.liferay.item.selector.taglib.servlet.taglib.ImageSelectorTag) _jspx_tagPool_liferay$1item$1selector_image$1selector_validExtensions_uploadURL_paramName_maxFileSize_itemSelectorURL_itemSelectorEventName_fileEntryId_draggableImage_nobody.get(com.liferay.item.selector.taglib.servlet.taglib.ImageSelectorTag.class);
            _jspx_th_liferay$1item$1selector_image$1selector_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1item$1selector_image$1selector_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_liferay$1item$1selector_image$1selector_0.setDraggableImage("vertical");
            _jspx_th_liferay$1item$1selector_image$1selector_0.setFileEntryId( coverImageFileEntryId );
            _jspx_th_liferay$1item$1selector_image$1selector_0.setItemSelectorEventName( coverImageSelectedItemEventName );
            _jspx_th_liferay$1item$1selector_image$1selector_0.setItemSelectorURL( blogsItemSelectorHelper.getItemSelectorURL(requestBackedPortletURLFactory, themeDisplay, coverImageSelectedItemEventName) );
            _jspx_th_liferay$1item$1selector_image$1selector_0.setMaxFileSize( PropsValues.BLOGS_IMAGE_MAX_SIZE );
            _jspx_th_liferay$1item$1selector_image$1selector_0.setParamName("coverImageFileEntry");
            _jspx_th_liferay$1item$1selector_image$1selector_0.setUploadURL( uploadCoverImageURL );
            _jspx_th_liferay$1item$1selector_image$1selector_0.setValidExtensions( StringUtil.merge(imageExtensions, ", ") );
            int _jspx_eval_liferay$1item$1selector_image$1selector_0 = _jspx_th_liferay$1item$1selector_image$1selector_0.doStartTag();
            if (_jspx_th_liferay$1item$1selector_image$1selector_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1item$1selector_image$1selector_validExtensions_uploadURL_paramName_maxFileSize_itemSelectorURL_itemSelectorEventName_fileEntryId_draggableImage_nobody.reuse(_jspx_th_liferay$1item$1selector_image$1selector_0);
              return;
            }
            _jspx_tagPool_liferay$1item$1selector_image$1selector_validExtensions_uploadURL_paramName_maxFileSize_itemSelectorURL_itemSelectorEventName_fileEntryId_draggableImage_nobody.reuse(_jspx_th_liferay$1item$1selector_image$1selector_0);
            out.write("\n\t\t\t\t\t</div>\n\n\t\t\t\t\t");
            if (_jspx_meth_aui_input_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_0, _jspx_page_context))
              return;
            out.write("\n\n\t\t\t\t\t<div class=\"col-md-8 col-md-offset-2\">\n\t\t\t\t\t\t<div class=\"cover-image-caption ");
            out.print( (coverImageFileEntryId == 0) ? "invisible" : "" );
            out.write("\">\n\t\t\t\t\t\t\t<small>\n\t\t\t\t\t\t\t\t");
            //  liferay-ui:input-editor
            com.liferay.taglib.ui.InputEditorTag _jspx_th_liferay$1ui_input$1editor_0 = (com.liferay.taglib.ui.InputEditorTag) _jspx_tagPool_liferay$1ui_input$1editor_showSource_placeholder_name_editorName_contents_nobody.get(com.liferay.taglib.ui.InputEditorTag.class);
            _jspx_th_liferay$1ui_input$1editor_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_input$1editor_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_liferay$1ui_input$1editor_0.setContents( coverImageCaption );
            _jspx_th_liferay$1ui_input$1editor_0.setEditorName("alloyeditor");
            _jspx_th_liferay$1ui_input$1editor_0.setName("coverImageCaptionEditor");
            _jspx_th_liferay$1ui_input$1editor_0.setPlaceholder("caption");
            _jspx_th_liferay$1ui_input$1editor_0.setShowSource( false );
            int _jspx_eval_liferay$1ui_input$1editor_0 = _jspx_th_liferay$1ui_input$1editor_0.doStartTag();
            if (_jspx_th_liferay$1ui_input$1editor_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_input$1editor_showSource_placeholder_name_editorName_contents_nobody.reuse(_jspx_th_liferay$1ui_input$1editor_0);
              return;
            }
            _jspx_tagPool_liferay$1ui_input$1editor_showSource_placeholder_name_editorName_contents_nobody.reuse(_jspx_th_liferay$1ui_input$1editor_0);
            out.write("\n\t\t\t\t\t\t\t</small>\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</div>\n\n\t\t\t\t\t<div class=\"col-md-8 col-md-offset-2\">\n\t\t\t\t\t\t<div class=\"entry-title form-group\">\n\t\t\t\t\t\t\t<h1>\n\t\t\t\t\t\t\t\t");
            //  liferay-ui:input-editor
            com.liferay.taglib.ui.InputEditorTag _jspx_th_liferay$1ui_input$1editor_1 = (com.liferay.taglib.ui.InputEditorTag) _jspx_tagPool_liferay$1ui_input$1editor_showSource_placeholder_onChangeMethod_name_editorName_contents_nobody.get(com.liferay.taglib.ui.InputEditorTag.class);
            _jspx_th_liferay$1ui_input$1editor_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_input$1editor_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_liferay$1ui_input$1editor_1.setContents( HtmlUtil.escape(title) );
            _jspx_th_liferay$1ui_input$1editor_1.setEditorName("alloyeditor");
            _jspx_th_liferay$1ui_input$1editor_1.setName("titleEditor");
            _jspx_th_liferay$1ui_input$1editor_1.setOnChangeMethod("OnChangeTitle");
            _jspx_th_liferay$1ui_input$1editor_1.setPlaceholder("title");
            _jspx_th_liferay$1ui_input$1editor_1.setShowSource( false );
            int _jspx_eval_liferay$1ui_input$1editor_1 = _jspx_th_liferay$1ui_input$1editor_1.doStartTag();
            if (_jspx_th_liferay$1ui_input$1editor_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_input$1editor_showSource_placeholder_onChangeMethod_name_editorName_contents_nobody.reuse(_jspx_th_liferay$1ui_input$1editor_1);
              return;
            }
            _jspx_tagPool_liferay$1ui_input$1editor_showSource_placeholder_onChangeMethod_name_editorName_contents_nobody.reuse(_jspx_th_liferay$1ui_input$1editor_1);
            out.write("\n\t\t\t\t\t\t\t</h1>\n\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t");
            if (_jspx_meth_aui_input_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_0, _jspx_page_context))
              return;
            out.write("\n\n\t\t\t\t\t\t<div class=\"entry-subtitle\">\n\t\t\t\t\t\t\t<h4>");
            //  liferay-ui:input-editor
            com.liferay.taglib.ui.InputEditorTag _jspx_th_liferay$1ui_input$1editor_2 = (com.liferay.taglib.ui.InputEditorTag) _jspx_tagPool_liferay$1ui_input$1editor_showSource_placeholder_name_editorName_contents_nobody.get(com.liferay.taglib.ui.InputEditorTag.class);
            _jspx_th_liferay$1ui_input$1editor_2.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_input$1editor_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_liferay$1ui_input$1editor_2.setContents( HtmlUtil.escape(subtitle) );
            _jspx_th_liferay$1ui_input$1editor_2.setEditorName("alloyeditor");
            _jspx_th_liferay$1ui_input$1editor_2.setName("subtitleEditor");
            _jspx_th_liferay$1ui_input$1editor_2.setPlaceholder("subtitle");
            _jspx_th_liferay$1ui_input$1editor_2.setShowSource( false );
            int _jspx_eval_liferay$1ui_input$1editor_2 = _jspx_th_liferay$1ui_input$1editor_2.doStartTag();
            if (_jspx_th_liferay$1ui_input$1editor_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_input$1editor_showSource_placeholder_name_editorName_contents_nobody.reuse(_jspx_th_liferay$1ui_input$1editor_2);
              return;
            }
            _jspx_tagPool_liferay$1ui_input$1editor_showSource_placeholder_name_editorName_contents_nobody.reuse(_jspx_th_liferay$1ui_input$1editor_2);
            out.write(" </h4>\n\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t");
            if (_jspx_meth_aui_input_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_0, _jspx_page_context))
              return;
            out.write("\n\n\t\t\t\t\t\t<div class=\"entry-content form-group\">\n\t\t\t\t\t\t\t");
            //  liferay-ui:input-editor
            com.liferay.taglib.ui.InputEditorTag _jspx_th_liferay$1ui_input$1editor_3 = (com.liferay.taglib.ui.InputEditorTag) _jspx_tagPool_liferay$1ui_input$1editor_required_placeholder_onChangeMethod_name_editorName_contents.get(com.liferay.taglib.ui.InputEditorTag.class);
            _jspx_th_liferay$1ui_input$1editor_3.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_input$1editor_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_liferay$1ui_input$1editor_3.setContents( content );
            _jspx_th_liferay$1ui_input$1editor_3.setEditorName( PropsUtil.get("editor.wysiwyg.portal-web.docroot.html.portlet.blogs.edit_entry.jsp") );
            _jspx_th_liferay$1ui_input$1editor_3.setName("contentEditor");
            _jspx_th_liferay$1ui_input$1editor_3.setOnChangeMethod("OnChangeEditor");
            _jspx_th_liferay$1ui_input$1editor_3.setPlaceholder("content");
            _jspx_th_liferay$1ui_input$1editor_3.setRequired( true );
            int _jspx_eval_liferay$1ui_input$1editor_3 = _jspx_th_liferay$1ui_input$1editor_3.doStartTag();
            if (_jspx_eval_liferay$1ui_input$1editor_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t\t\t\t");
              if (_jspx_meth_aui_validator_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_input$1editor_3, _jspx_page_context))
                return;
              out.write("\n\t\t\t\t\t\t\t");
            }
            if (_jspx_th_liferay$1ui_input$1editor_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_input$1editor_required_placeholder_onChangeMethod_name_editorName_contents.reuse(_jspx_th_liferay$1ui_input$1editor_3);
              return;
            }
            _jspx_tagPool_liferay$1ui_input$1editor_required_placeholder_onChangeMethod_name_editorName_contents.reuse(_jspx_th_liferay$1ui_input$1editor_3);
            out.write("\n\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t");
            if (_jspx_meth_aui_input_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_0, _jspx_page_context))
              return;
            out.write("\n\t\t\t\t\t</div>\n\t\t\t\t");
          }
          if (_jspx_th_aui_fieldset_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_0);
            return;
          }
          _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_0);
          out.write("\n\n\t\t\t\t");
          //  aui:fieldset
          com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_1 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.get(com.liferay.taglib.aui.FieldsetTag.class);
          _jspx_th_aui_fieldset_1.setPageContext(_jspx_page_context);
          _jspx_th_aui_fieldset_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset$1group_0);
          _jspx_th_aui_fieldset_1.setCollapsed( true );
          _jspx_th_aui_fieldset_1.setCollapsible( true );
          _jspx_th_aui_fieldset_1.setLabel("categorization");
          int _jspx_eval_aui_fieldset_1 = _jspx_th_aui_fieldset_1.doStartTag();
          if (_jspx_eval_aui_fieldset_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t");
            //  liferay-asset:asset-categories-selector
            com.liferay.asset.taglib.servlet.taglib.AssetCategoriesSelectorTag _jspx_th_liferay$1asset_asset$1categories$1selector_0 = (com.liferay.asset.taglib.servlet.taglib.AssetCategoriesSelectorTag) _jspx_tagPool_liferay$1asset_asset$1categories$1selector_classPK_className_nobody.get(com.liferay.asset.taglib.servlet.taglib.AssetCategoriesSelectorTag.class);
            _jspx_th_liferay$1asset_asset$1categories$1selector_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1asset_asset$1categories$1selector_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_liferay$1asset_asset$1categories$1selector_0.setClassName( BlogsEntry.class.getName() );
            _jspx_th_liferay$1asset_asset$1categories$1selector_0.setClassPK( entryId );
            int _jspx_eval_liferay$1asset_asset$1categories$1selector_0 = _jspx_th_liferay$1asset_asset$1categories$1selector_0.doStartTag();
            if (_jspx_th_liferay$1asset_asset$1categories$1selector_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1asset_asset$1categories$1selector_classPK_className_nobody.reuse(_jspx_th_liferay$1asset_asset$1categories$1selector_0);
              return;
            }
            _jspx_tagPool_liferay$1asset_asset$1categories$1selector_classPK_className_nobody.reuse(_jspx_th_liferay$1asset_asset$1categories$1selector_0);
            out.write("\n\n\t\t\t\t\t");
            //  liferay-asset:asset-tags-selector
            com.liferay.asset.taglib.servlet.taglib.AssetTagsSelectorTag _jspx_th_liferay$1asset_asset$1tags$1selector_0 = (com.liferay.asset.taglib.servlet.taglib.AssetTagsSelectorTag) _jspx_tagPool_liferay$1asset_asset$1tags$1selector_classPK_className_nobody.get(com.liferay.asset.taglib.servlet.taglib.AssetTagsSelectorTag.class);
            _jspx_th_liferay$1asset_asset$1tags$1selector_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1asset_asset$1tags$1selector_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_liferay$1asset_asset$1tags$1selector_0.setClassName( BlogsEntry.class.getName() );
            _jspx_th_liferay$1asset_asset$1tags$1selector_0.setClassPK( entryId );
            int _jspx_eval_liferay$1asset_asset$1tags$1selector_0 = _jspx_th_liferay$1asset_asset$1tags$1selector_0.doStartTag();
            if (_jspx_th_liferay$1asset_asset$1tags$1selector_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1asset_asset$1tags$1selector_classPK_className_nobody.reuse(_jspx_th_liferay$1asset_asset$1tags$1selector_0);
              return;
            }
            _jspx_tagPool_liferay$1asset_asset$1tags$1selector_classPK_className_nobody.reuse(_jspx_th_liferay$1asset_asset$1tags$1selector_0);
            out.write("\n\t\t\t\t");
          }
          if (_jspx_th_aui_fieldset_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.reuse(_jspx_th_aui_fieldset_1);
            return;
          }
          _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.reuse(_jspx_th_aui_fieldset_1);
          out.write("\n\n\t\t\t\t");
          //  aui:fieldset
          com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_2 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.get(com.liferay.taglib.aui.FieldsetTag.class);
          _jspx_th_aui_fieldset_2.setPageContext(_jspx_page_context);
          _jspx_th_aui_fieldset_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset$1group_0);
          _jspx_th_aui_fieldset_2.setCollapsed( true );
          _jspx_th_aui_fieldset_2.setCollapsible( true );
          _jspx_th_aui_fieldset_2.setLabel("related-assets");
          int _jspx_eval_aui_fieldset_2 = _jspx_th_aui_fieldset_2.doStartTag();
          if (_jspx_eval_aui_fieldset_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t");
            //  liferay-asset:input-asset-links
            com.liferay.asset.taglib.servlet.taglib.InputAssetLinksTag _jspx_th_liferay$1asset_input$1asset$1links_0 = (com.liferay.asset.taglib.servlet.taglib.InputAssetLinksTag) _jspx_tagPool_liferay$1asset_input$1asset$1links_classPK_className_nobody.get(com.liferay.asset.taglib.servlet.taglib.InputAssetLinksTag.class);
            _jspx_th_liferay$1asset_input$1asset$1links_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1asset_input$1asset$1links_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
            _jspx_th_liferay$1asset_input$1asset$1links_0.setClassName( BlogsEntry.class.getName() );
            _jspx_th_liferay$1asset_input$1asset$1links_0.setClassPK( entryId );
            int _jspx_eval_liferay$1asset_input$1asset$1links_0 = _jspx_th_liferay$1asset_input$1asset$1links_0.doStartTag();
            if (_jspx_th_liferay$1asset_input$1asset$1links_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1asset_input$1asset$1links_classPK_className_nobody.reuse(_jspx_th_liferay$1asset_input$1asset$1links_0);
              return;
            }
            _jspx_tagPool_liferay$1asset_input$1asset$1links_classPK_className_nobody.reuse(_jspx_th_liferay$1asset_input$1asset$1links_0);
            out.write("\n\t\t\t\t");
          }
          if (_jspx_th_aui_fieldset_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.reuse(_jspx_th_aui_fieldset_2);
            return;
          }
          _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.reuse(_jspx_th_aui_fieldset_2);
          out.write("\n\n\t\t\t\t");
          //  aui:fieldset
          com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_3 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.get(com.liferay.taglib.aui.FieldsetTag.class);
          _jspx_th_aui_fieldset_3.setPageContext(_jspx_page_context);
          _jspx_th_aui_fieldset_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset$1group_0);
          _jspx_th_aui_fieldset_3.setCollapsed( true );
          _jspx_th_aui_fieldset_3.setCollapsible( true );
          _jspx_th_aui_fieldset_3.setLabel("configuration");
          int _jspx_eval_aui_fieldset_3 = _jspx_th_aui_fieldset_3.doStartTag();
          if (_jspx_eval_aui_fieldset_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\n\t\t\t\t\t");

					Portlet portlet = PortletLocalServiceUtil.getPortletById(BlogsPortletKeys.BLOGS);

					String friendlyURLPrefix = StringUtil.shorten("/-/" + portlet.getFriendlyURLMapping(), 40) + StringPool.SLASH;
					
            out.write("\n\n\t\t\t\t\t<div class=\"clearfix form-group\">\n\n\t\t\t\t\t\t");

						boolean automaticURL;

						if (entry == null) {
							automaticURL = Validator.isNull(urlTitle);
						}
						else {
							String uniqueUrlTitle = BlogsEntryLocalServiceUtil.getUniqueUrlTitle(entry);

							automaticURL = uniqueUrlTitle.equals(urlTitle);
						}
						
            out.write("\n\n\t\t\t\t\t\t<h4>\n\t\t\t\t\t\t\t");
            if (_jspx_meth_liferay$1ui_message_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_3, _jspx_page_context))
              return;
            out.write("\n\t\t\t\t\t\t</h4>\n\n\t\t\t\t\t\t<div class=\"form-group\" id=\"");
            if (_jspx_meth_portlet_namespace_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_3, _jspx_page_context))
              return;
            out.write("urlOptions\">\n\t\t\t\t\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_9 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_helpMessage_checked_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_9.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
            _jspx_th_aui_input_9.setChecked( automaticURL );
            _jspx_th_aui_input_9.setHelpMessage("the-url-will-be-based-on-the-entry-title");
            _jspx_th_aui_input_9.setLabel("automatic");
            _jspx_th_aui_input_9.setName("automaticURL");
            _jspx_th_aui_input_9.setType("radio");
            _jspx_th_aui_input_9.setValue( true );
            int _jspx_eval_aui_input_9 = _jspx_th_aui_input_9.doStartTag();
            if (_jspx_th_aui_input_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_label_helpMessage_checked_nobody.reuse(_jspx_th_aui_input_9);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_label_helpMessage_checked_nobody.reuse(_jspx_th_aui_input_9);
            out.write("\n\n\t\t\t\t\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_10 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_10.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
            _jspx_th_aui_input_10.setChecked( !automaticURL );
            _jspx_th_aui_input_10.setLabel("custom");
            _jspx_th_aui_input_10.setName("automaticURL");
            _jspx_th_aui_input_10.setType("radio");
            _jspx_th_aui_input_10.setValue( false );
            int _jspx_eval_aui_input_10 = _jspx_th_aui_input_10.doStartTag();
            if (_jspx_th_aui_input_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_10);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_10);
            out.write("\n\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t");
            //  aui:field-wrapper
            com.liferay.taglib.aui.FieldWrapperTag _jspx_th_aui_field$1wrapper_0 = (com.liferay.taglib.aui.FieldWrapperTag) _jspx_tagPool_aui_field$1wrapper_name_label_helpMessage_disabled_cssClass.get(com.liferay.taglib.aui.FieldWrapperTag.class);
            _jspx_th_aui_field$1wrapper_0.setPageContext(_jspx_page_context);
            _jspx_th_aui_field$1wrapper_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
            _jspx_th_aui_field$1wrapper_0.setCssClass("form-group");
            _jspx_th_aui_field$1wrapper_0.setDisabled( automaticURL );
            _jspx_th_aui_field$1wrapper_0.setHelpMessage( LanguageUtil.format(resourceBundle, "for-example-x", "<em>one-day-in-the-life-of-marion-cotillard</em>") );
            _jspx_th_aui_field$1wrapper_0.setLabel("blog-entry-url");
            _jspx_th_aui_field$1wrapper_0.setName("urlTitle");
            int _jspx_eval_aui_field$1wrapper_0 = _jspx_th_aui_field$1wrapper_0.doStartTag();
            if (_jspx_eval_aui_field$1wrapper_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t\t\t<div class=\"form-text\">");
              out.print( friendlyURLPrefix );
              out.write("</div>\n\n\t\t\t\t\t\t\t");
              //  aui:input
              com.liferay.taglib.aui.InputTag _jspx_th_aui_input_11 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_prefix_name_label_ignoreRequestValue_disabled_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
              _jspx_th_aui_input_11.setPageContext(_jspx_page_context);
              _jspx_th_aui_input_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_field$1wrapper_0);
              _jspx_th_aui_input_11.setCssClass("input-medium");
              _jspx_th_aui_input_11.setDisabled( automaticURL );
              _jspx_th_aui_input_11.setIgnoreRequestValue( true );
              _jspx_th_aui_input_11.setLabel("");
              _jspx_th_aui_input_11.setName("urlTitle");
              _jspx_th_aui_input_11.setPrefix("/");
              _jspx_th_aui_input_11.setType("text");
              _jspx_th_aui_input_11.setValue( urlTitle );
              int _jspx_eval_aui_input_11 = _jspx_th_aui_input_11.doStartTag();
              if (_jspx_th_aui_input_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_input_value_type_prefix_name_label_ignoreRequestValue_disabled_cssClass_nobody.reuse(_jspx_th_aui_input_11);
                return;
              }
              _jspx_tagPool_aui_input_value_type_prefix_name_label_ignoreRequestValue_disabled_cssClass_nobody.reuse(_jspx_th_aui_input_11);
              out.write("\n\t\t\t\t\t\t");
            }
            if (_jspx_th_aui_field$1wrapper_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_field$1wrapper_name_label_helpMessage_disabled_cssClass.reuse(_jspx_th_aui_field$1wrapper_0);
              return;
            }
            _jspx_tagPool_aui_field$1wrapper_name_label_helpMessage_disabled_cssClass.reuse(_jspx_th_aui_field$1wrapper_0);
            out.write("\n\t\t\t\t\t</div>\n\n\t\t\t\t\t<div class=\"clearfix form-group\">\n\t\t\t\t\t\t<label>");
            if (_jspx_meth_liferay$1ui_message_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_3, _jspx_page_context))
              return;
            out.write(' ');
            if (_jspx_meth_liferay$1ui_icon$1help_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_3, _jspx_page_context))
              return;
            out.write("</label>\n\n\t\t\t\t\t\t");
            //  liferay-ui:error
            com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_9 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_exception.get(com.liferay.taglib.ui.ErrorTag.class);
            _jspx_th_liferay$1ui_error_9.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_error_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
            _jspx_th_liferay$1ui_error_9.setException( EntrySmallImageNameException.class );
            int _jspx_eval_liferay$1ui_error_9 = _jspx_th_liferay$1ui_error_9.doStartTag();
            if (_jspx_eval_liferay$1ui_error_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              java.lang.Object errorException = null;
              if (_jspx_eval_liferay$1ui_error_9 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_liferay$1ui_error_9.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_liferay$1ui_error_9.doInitBody();
              }
              errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
              do {
                out.write("\n\t\t\t\t\t\t\t");
                if (_jspx_meth_liferay$1ui_message_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_error_9, _jspx_page_context))
                  return;
                out.write(' ');
                out.print( StringUtil.merge(imageExtensions, ", ") );
                out.write(".\n\t\t\t\t\t\t");
                int evalDoAfterBody = _jspx_th_liferay$1ui_error_9.doAfterBody();
                errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_liferay$1ui_error_9 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_liferay$1ui_error_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_9);
              return;
            }
            _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_9);
            out.write("\n\n\t\t\t\t\t\t");
            //  liferay-ui:error
            com.liferay.taglib.ui.ErrorTag _jspx_th_liferay$1ui_error_10 = (com.liferay.taglib.ui.ErrorTag) _jspx_tagPool_liferay$1ui_error_exception.get(com.liferay.taglib.ui.ErrorTag.class);
            _jspx_th_liferay$1ui_error_10.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_error_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
            _jspx_th_liferay$1ui_error_10.setException( EntrySmallImageScaleException.class );
            int _jspx_eval_liferay$1ui_error_10 = _jspx_th_liferay$1ui_error_10.doStartTag();
            if (_jspx_eval_liferay$1ui_error_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              java.lang.Object errorException = null;
              if (_jspx_eval_liferay$1ui_error_10 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_liferay$1ui_error_10.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_liferay$1ui_error_10.doInitBody();
              }
              errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
              do {
                out.write("\n\t\t\t\t\t\t\t");
                if (_jspx_meth_liferay$1ui_message_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_error_10, _jspx_page_context))
                  return;
                out.write("\n\t\t\t\t\t\t");
                int evalDoAfterBody = _jspx_th_liferay$1ui_error_10.doAfterBody();
                errorException = (java.lang.Object) _jspx_page_context.findAttribute("errorException");
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_liferay$1ui_error_10 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_liferay$1ui_error_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_10);
              return;
            }
            _jspx_tagPool_liferay$1ui_error_exception.reuse(_jspx_th_liferay$1ui_error_10);
            out.write("\n\n\t\t\t\t\t\t<div class=\"form-group\" id=\"");
            if (_jspx_meth_portlet_namespace_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_3, _jspx_page_context))
              return;
            out.write("entryAbstractOptions\">\n\t\t\t\t\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_12 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_12.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
            _jspx_th_aui_input_12.setChecked( !customAbstract );
            _jspx_th_aui_input_12.setLabel( LanguageUtil.format(request, "use-the-first-x-characters-of-the-entry-content", pageAbstractLength, false) );
            _jspx_th_aui_input_12.setName("customAbstract");
            _jspx_th_aui_input_12.setType("radio");
            _jspx_th_aui_input_12.setValue( false );
            int _jspx_eval_aui_input_12 = _jspx_th_aui_input_12.doStartTag();
            if (_jspx_th_aui_input_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_12);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_12);
            out.write("\n\n\t\t\t\t\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_13 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_13.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
            _jspx_th_aui_input_13.setChecked( customAbstract );
            _jspx_th_aui_input_13.setLabel("custom-abstract");
            _jspx_th_aui_input_13.setName("customAbstract");
            _jspx_th_aui_input_13.setType("radio");
            _jspx_th_aui_input_13.setValue( true );
            int _jspx_eval_aui_input_13 = _jspx_th_aui_input_13.doStartTag();
            if (_jspx_th_aui_input_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_13);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_label_checked_nobody.reuse(_jspx_th_aui_input_13);
            out.write("\n\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t<div class=\"entry-description form-group\">\n\t\t\t\t\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_14 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_disabled.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_14.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
            _jspx_th_aui_input_14.setDisabled( !customAbstract );
            _jspx_th_aui_input_14.setLabel("description");
            _jspx_th_aui_input_14.setName("description");
            _jspx_th_aui_input_14.setType("text");
            _jspx_th_aui_input_14.setValue( description );
            int _jspx_eval_aui_input_14 = _jspx_th_aui_input_14.doStartTag();
            if (_jspx_eval_aui_input_14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t\t\t\t");
              if (_jspx_meth_aui_validator_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_input_14, _jspx_page_context))
                return;
              out.write("\n\t\t\t\t\t\t\t");
            }
            if (_jspx_th_aui_input_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_type_name_label_disabled.reuse(_jspx_th_aui_input_14);
              return;
            }
            _jspx_tagPool_aui_input_value_type_name_label_disabled.reuse(_jspx_th_aui_input_14);
            out.write("\n\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t");
            //  portlet:actionURL
            com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_2 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name_nobody.get(com.liferay.taglib.portlet.ActionURLTag.class);
            _jspx_th_portlet_actionURL_2.setPageContext(_jspx_page_context);
            _jspx_th_portlet_actionURL_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
            _jspx_th_portlet_actionURL_2.setName("/blogs/upload_small_image");
            _jspx_th_portlet_actionURL_2.setVar("uploadSmallImageURL");
            int _jspx_eval_portlet_actionURL_2 = _jspx_th_portlet_actionURL_2.doStartTag();
            if (_jspx_th_portlet_actionURL_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_actionURL_var_name_nobody.reuse(_jspx_th_portlet_actionURL_2);
              return;
            }
            _jspx_tagPool_portlet_actionURL_var_name_nobody.reuse(_jspx_th_portlet_actionURL_2);
            java.lang.String uploadSmallImageURL = null;
            uploadSmallImageURL = (java.lang.String) _jspx_page_context.findAttribute("uploadSmallImageURL");
            out.write("\n\n\t\t\t\t\t\t<div class=\"clearfix\">\n\t\t\t\t\t\t\t<label class=\"control-label\">");
            if (_jspx_meth_liferay$1ui_message_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_3, _jspx_page_context))
              return;
            out.write("</label>\n\t\t\t\t\t\t</div>\n\n\t\t\t\t\t\t<div class=\"lfr-blogs-small-image-selector\">\n\n\t\t\t\t\t\t\t");

							String smallImageSelectedItemEventName = liferayPortletResponse.getNamespace() + "smallImageSelectedItem";
							
            out.write("\n\n\t\t\t\t\t\t\t");
            //  liferay-item-selector:image-selector
            com.liferay.item.selector.taglib.servlet.taglib.ImageSelectorTag _jspx_th_liferay$1item$1selector_image$1selector_1 = (com.liferay.item.selector.taglib.servlet.taglib.ImageSelectorTag) _jspx_tagPool_liferay$1item$1selector_image$1selector_validExtensions_uploadURL_paramName_maxFileSize_itemSelectorURL_itemSelectorEventName_fileEntryId_nobody.get(com.liferay.item.selector.taglib.servlet.taglib.ImageSelectorTag.class);
            _jspx_th_liferay$1item$1selector_image$1selector_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1item$1selector_image$1selector_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
            _jspx_th_liferay$1item$1selector_image$1selector_1.setFileEntryId( smallImageFileEntryId );
            _jspx_th_liferay$1item$1selector_image$1selector_1.setItemSelectorEventName( smallImageSelectedItemEventName );
            _jspx_th_liferay$1item$1selector_image$1selector_1.setItemSelectorURL( blogsItemSelectorHelper.getItemSelectorURL(requestBackedPortletURLFactory, themeDisplay, smallImageSelectedItemEventName) );
            _jspx_th_liferay$1item$1selector_image$1selector_1.setMaxFileSize( PropsValues.BLOGS_IMAGE_MAX_SIZE );
            _jspx_th_liferay$1item$1selector_image$1selector_1.setParamName("smallImageFileEntry");
            _jspx_th_liferay$1item$1selector_image$1selector_1.setUploadURL( uploadSmallImageURL );
            _jspx_th_liferay$1item$1selector_image$1selector_1.setValidExtensions( StringUtil.merge(imageExtensions, ", ") );
            int _jspx_eval_liferay$1item$1selector_image$1selector_1 = _jspx_th_liferay$1item$1selector_image$1selector_1.doStartTag();
            if (_jspx_th_liferay$1item$1selector_image$1selector_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1item$1selector_image$1selector_validExtensions_uploadURL_paramName_maxFileSize_itemSelectorURL_itemSelectorEventName_fileEntryId_nobody.reuse(_jspx_th_liferay$1item$1selector_image$1selector_1);
              return;
            }
            _jspx_tagPool_liferay$1item$1selector_image$1selector_validExtensions_uploadURL_paramName_maxFileSize_itemSelectorURL_itemSelectorEventName_fileEntryId_nobody.reuse(_jspx_th_liferay$1item$1selector_image$1selector_1);
            out.write("\n\t\t\t\t\t\t</div>\n\t\t\t\t\t</div>\n\n\t\t\t\t\t");
            if (_jspx_meth_aui_input_15((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_fieldset_3, _jspx_page_context))
              return;
            out.write("\n\n\t\t\t\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_3.setPageContext(_jspx_page_context);
            _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
            _jspx_th_c_if_3.setTest( (entry != null) && blogsGroupServiceSettings.isEmailEntryUpdatedEnabled() );
            int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
            if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\n\t\t\t\t\t\t");

						boolean sendEmailEntryUpdated = ParamUtil.getBoolean(request, "sendEmailEntryUpdated");
						
              out.write("\n\n\t\t\t\t\t\t");
              //  aui:input
              com.liferay.taglib.aui.InputTag _jspx_th_aui_input_16 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody.get(com.liferay.taglib.aui.InputTag.class);
              _jspx_th_aui_input_16.setPageContext(_jspx_page_context);
              _jspx_th_aui_input_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
              _jspx_th_aui_input_16.setHelpMessage("comments-regarding-the-blog-entry-update");
              _jspx_th_aui_input_16.setLabel("send-email-entry-updated");
              _jspx_th_aui_input_16.setName("sendEmailEntryUpdated");
              _jspx_th_aui_input_16.setType("toggle-switch");
              _jspx_th_aui_input_16.setValue( sendEmailEntryUpdated );
              int _jspx_eval_aui_input_16 = _jspx_th_aui_input_16.doStartTag();
              if (_jspx_th_aui_input_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody.reuse(_jspx_th_aui_input_16);
                return;
              }
              _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody.reuse(_jspx_th_aui_input_16);
              out.write("\n\n\t\t\t\t\t\t");

						String emailEntryUpdatedComment = ParamUtil.getString(request, "emailEntryUpdatedComment");
						
              out.write("\n\n\t\t\t\t\t\t<div id=\"");
              if (_jspx_meth_portlet_namespace_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_3, _jspx_page_context))
                return;
              out.write("emailEntryUpdatedCommentWrapper\">\n\t\t\t\t\t\t\t");
              //  aui:input
              com.liferay.taglib.aui.InputTag _jspx_th_aui_input_17 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
              _jspx_th_aui_input_17.setPageContext(_jspx_page_context);
              _jspx_th_aui_input_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
              _jspx_th_aui_input_17.setLabel("");
              _jspx_th_aui_input_17.setName("emailEntryUpdatedComment");
              _jspx_th_aui_input_17.setType("textarea");
              _jspx_th_aui_input_17.setValue( emailEntryUpdatedComment );
              int _jspx_eval_aui_input_17 = _jspx_th_aui_input_17.doStartTag();
              if (_jspx_th_aui_input_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_17);
                return;
              }
              _jspx_tagPool_aui_input_value_type_name_label_nobody.reuse(_jspx_th_aui_input_17);
              out.write("\n\t\t\t\t\t\t</div>\n\t\t\t\t\t");
            }
            if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
            out.write("\n\n\t\t\t\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_4.setPageContext(_jspx_page_context);
            _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
            _jspx_th_c_if_4.setTest( PropsValues.BLOGS_PINGBACK_ENABLED );
            int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
            if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t\t");
              //  aui:input
              com.liferay.taglib.aui.InputTag _jspx_th_aui_input_18 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody.get(com.liferay.taglib.aui.InputTag.class);
              _jspx_th_aui_input_18.setPageContext(_jspx_page_context);
              _jspx_th_aui_input_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_4);
              _jspx_th_aui_input_18.setHelpMessage( LanguageUtil.get(resourceBundle, "a-pingback-is-a-comment-that-is-created-when-you-link-to-another-blog-post-where-pingbacks-are-enabled") + " " + LanguageUtil.get(resourceBundle, "to-allow-pingbacks,-please-also-ensure-the-entry's-guest-view-permission-is-enabled") );
              _jspx_th_aui_input_18.setLabel("allow-pingbacks");
              _jspx_th_aui_input_18.setName("allowPingbacks");
              _jspx_th_aui_input_18.setType("toggle-switch");
              _jspx_th_aui_input_18.setValue( allowPingbacks );
              int _jspx_eval_aui_input_18 = _jspx_th_aui_input_18.doStartTag();
              if (_jspx_th_aui_input_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody.reuse(_jspx_th_aui_input_18);
                return;
              }
              _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody.reuse(_jspx_th_aui_input_18);
              out.write("\n\t\t\t\t\t");
            }
            if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
            out.write("\n\n\t\t\t\t\t");
            //  c:if
            com.liferay.taglib.core.IfTag _jspx_th_c_if_5 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
            _jspx_th_c_if_5.setPageContext(_jspx_page_context);
            _jspx_th_c_if_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
            _jspx_th_c_if_5.setTest( PropsValues.BLOGS_TRACKBACK_ENABLED );
            int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
            if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t\t");
              //  aui:input
              com.liferay.taglib.aui.InputTag _jspx_th_aui_input_19 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody.get(com.liferay.taglib.aui.InputTag.class);
              _jspx_th_aui_input_19.setPageContext(_jspx_page_context);
              _jspx_th_aui_input_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
              _jspx_th_aui_input_19.setHelpMessage("to-allow-trackbacks,-please-also-ensure-the-entry's-guest-view-permission-is-enabled");
              _jspx_th_aui_input_19.setLabel("allow-trackbacks");
              _jspx_th_aui_input_19.setName("allowTrackbacks");
              _jspx_th_aui_input_19.setType("toggle-switch");
              _jspx_th_aui_input_19.setValue( allowTrackbacks );
              int _jspx_eval_aui_input_19 = _jspx_th_aui_input_19.doStartTag();
              if (_jspx_th_aui_input_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody.reuse(_jspx_th_aui_input_19);
                return;
              }
              _jspx_tagPool_aui_input_value_type_name_label_helpMessage_nobody.reuse(_jspx_th_aui_input_19);
              out.write("\n\n\t\t\t\t\t\t");
              if (_jspx_meth_aui_input_20((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_5, _jspx_page_context))
                return;
              out.write("\n\n\t\t\t\t\t\t");
              //  c:if
              com.liferay.taglib.core.IfTag _jspx_th_c_if_6 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
              _jspx_th_c_if_6.setPageContext(_jspx_page_context);
              _jspx_th_c_if_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
              _jspx_th_c_if_6.setTest( (entry != null) && Validator.isNotNull(entry.getTrackbacks()) );
              int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
              if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\n\t\t\t\t\t\t\t");

							int i = 0;

							for (String trackback : StringUtil.split(entry.getTrackbacks())) {
							
                out.write("\n\n\t\t\t\t\t\t\t\t");
                //  aui:input
                com.liferay.taglib.aui.InputTag _jspx_th_aui_input_21 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_title_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
                _jspx_th_aui_input_21.setPageContext(_jspx_page_context);
                _jspx_th_aui_input_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
                _jspx_th_aui_input_21.setLabel("");
                _jspx_th_aui_input_21.setName( "trackback" + i++ );
                _jspx_th_aui_input_21.setTitle("");
                _jspx_th_aui_input_21.setType("resource");
                _jspx_th_aui_input_21.setValue( trackback );
                int _jspx_eval_aui_input_21 = _jspx_th_aui_input_21.doStartTag();
                if (_jspx_th_aui_input_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_input_value_type_title_name_label_nobody.reuse(_jspx_th_aui_input_21);
                  return;
                }
                _jspx_tagPool_aui_input_value_type_title_name_label_nobody.reuse(_jspx_th_aui_input_21);
                out.write("\n\n\t\t\t\t\t\t\t");

							}
							
                out.write("\n\n\t\t\t\t\t\t");
              }
              if (_jspx_th_c_if_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                return;
              }
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
              out.write("\n\t\t\t\t\t");
            }
            if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
              return;
            }
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
            out.write("\n\t\t\t\t");
          }
          if (_jspx_th_aui_fieldset_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.reuse(_jspx_th_aui_fieldset_3);
            return;
          }
          _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.reuse(_jspx_th_aui_fieldset_3);
          out.write("\n\n\t\t\t\t");
          //  liferay-expando:custom-attributes-available
          com.liferay.expando.taglib.servlet.taglib.CustomAttributesAvailableTag _jspx_th_liferay$1expando_custom$1attributes$1available_0 = (com.liferay.expando.taglib.servlet.taglib.CustomAttributesAvailableTag) _jspx_tagPool_liferay$1expando_custom$1attributes$1available_className.get(com.liferay.expando.taglib.servlet.taglib.CustomAttributesAvailableTag.class);
          _jspx_th_liferay$1expando_custom$1attributes$1available_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1expando_custom$1attributes$1available_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset$1group_0);
          _jspx_th_liferay$1expando_custom$1attributes$1available_0.setClassName( BlogsEntry.class.getName() );
          int _jspx_eval_liferay$1expando_custom$1attributes$1available_0 = _jspx_th_liferay$1expando_custom$1attributes$1available_0.doStartTag();
          if (_jspx_eval_liferay$1expando_custom$1attributes$1available_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t");
            //  aui:fieldset
            com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_4 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.get(com.liferay.taglib.aui.FieldsetTag.class);
            _jspx_th_aui_fieldset_4.setPageContext(_jspx_page_context);
            _jspx_th_aui_fieldset_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1expando_custom$1attributes$1available_0);
            _jspx_th_aui_fieldset_4.setCollapsed( true );
            _jspx_th_aui_fieldset_4.setCollapsible( true );
            _jspx_th_aui_fieldset_4.setLabel("custom-fields");
            int _jspx_eval_aui_fieldset_4 = _jspx_th_aui_fieldset_4.doStartTag();
            if (_jspx_eval_aui_fieldset_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t\t");
              //  liferay-expando:custom-attribute-list
              com.liferay.expando.taglib.servlet.taglib.CustomAttributeListTag _jspx_th_liferay$1expando_custom$1attribute$1list_0 = (com.liferay.expando.taglib.servlet.taglib.CustomAttributeListTag) _jspx_tagPool_liferay$1expando_custom$1attribute$1list_label_editable_classPK_className_nobody.get(com.liferay.expando.taglib.servlet.taglib.CustomAttributeListTag.class);
              _jspx_th_liferay$1expando_custom$1attribute$1list_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1expando_custom$1attribute$1list_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_4);
              _jspx_th_liferay$1expando_custom$1attribute$1list_0.setClassName( BlogsEntry.class.getName() );
              _jspx_th_liferay$1expando_custom$1attribute$1list_0.setClassPK( entryId );
              _jspx_th_liferay$1expando_custom$1attribute$1list_0.setEditable( true );
              _jspx_th_liferay$1expando_custom$1attribute$1list_0.setLabel( true );
              int _jspx_eval_liferay$1expando_custom$1attribute$1list_0 = _jspx_th_liferay$1expando_custom$1attribute$1list_0.doStartTag();
              if (_jspx_th_liferay$1expando_custom$1attribute$1list_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1expando_custom$1attribute$1list_label_editable_classPK_className_nobody.reuse(_jspx_th_liferay$1expando_custom$1attribute$1list_0);
                return;
              }
              _jspx_tagPool_liferay$1expando_custom$1attribute$1list_label_editable_classPK_className_nobody.reuse(_jspx_th_liferay$1expando_custom$1attribute$1list_0);
              out.write("\n\t\t\t\t\t");
            }
            if (_jspx_th_aui_fieldset_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.reuse(_jspx_th_aui_fieldset_4);
              return;
            }
            _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.reuse(_jspx_th_aui_fieldset_4);
            out.write("\n\t\t\t\t");
          }
          if (_jspx_th_liferay$1expando_custom$1attributes$1available_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1expando_custom$1attributes$1available_className.reuse(_jspx_th_liferay$1expando_custom$1attributes$1available_0);
            return;
          }
          _jspx_tagPool_liferay$1expando_custom$1attributes$1available_className.reuse(_jspx_th_liferay$1expando_custom$1attributes$1available_0);
          out.write("\n\n\t\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_7 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_7.setPageContext(_jspx_page_context);
          _jspx_th_c_if_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset$1group_0);
          _jspx_th_c_if_7.setTest( (entry == null) || (entry.getStatus() == WorkflowConstants.STATUS_DRAFT) );
          int _jspx_eval_c_if_7 = _jspx_th_c_if_7.doStartTag();
          if (_jspx_eval_c_if_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t");
            //  aui:fieldset
            com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_5 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.get(com.liferay.taglib.aui.FieldsetTag.class);
            _jspx_th_aui_fieldset_5.setPageContext(_jspx_page_context);
            _jspx_th_aui_fieldset_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
            _jspx_th_aui_fieldset_5.setCollapsed( true );
            _jspx_th_aui_fieldset_5.setCollapsible( true );
            _jspx_th_aui_fieldset_5.setLabel("permissions");
            int _jspx_eval_aui_fieldset_5 = _jspx_th_aui_fieldset_5.doStartTag();
            if (_jspx_eval_aui_fieldset_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t\t\t");
              //  liferay-ui:input-permissions
              com.liferay.taglib.ui.InputPermissionsTag _jspx_th_liferay$1ui_input$1permissions_0 = (com.liferay.taglib.ui.InputPermissionsTag) _jspx_tagPool_liferay$1ui_input$1permissions_modelName_nobody.get(com.liferay.taglib.ui.InputPermissionsTag.class);
              _jspx_th_liferay$1ui_input$1permissions_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_input$1permissions_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_5);
              _jspx_th_liferay$1ui_input$1permissions_0.setModelName( BlogsEntry.class.getName() );
              int _jspx_eval_liferay$1ui_input$1permissions_0 = _jspx_th_liferay$1ui_input$1permissions_0.doStartTag();
              if (_jspx_th_liferay$1ui_input$1permissions_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_input$1permissions_modelName_nobody.reuse(_jspx_th_liferay$1ui_input$1permissions_0);
                return;
              }
              _jspx_tagPool_liferay$1ui_input$1permissions_modelName_nobody.reuse(_jspx_th_liferay$1ui_input$1permissions_0);
              out.write("\n\t\t\t\t\t");
            }
            if (_jspx_th_aui_fieldset_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.reuse(_jspx_th_aui_fieldset_5);
              return;
            }
            _jspx_tagPool_aui_fieldset_label_collapsible_collapsed.reuse(_jspx_th_aui_fieldset_5);
            out.write("\n\t\t\t\t");
          }
          if (_jspx_th_c_if_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
          out.write("\n\t\t\t");
        }
        if (_jspx_th_aui_fieldset$1group_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_fieldset$1group_markupView.reuse(_jspx_th_aui_fieldset$1group_0);
          return;
        }
        _jspx_tagPool_aui_fieldset$1group_markupView.reuse(_jspx_th_aui_fieldset$1group_0);
        out.write("\n\n\t\t\t");

			boolean pending = false;

			if (entry != null) {
				pending = entry.isPending();
			}
			
        out.write("\n\n\t\t\t");
        //  c:if
        com.liferay.taglib.core.IfTag _jspx_th_c_if_8 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
        _jspx_th_c_if_8.setPageContext(_jspx_page_context);
        _jspx_th_c_if_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_c_if_8.setTest( pending );
        int _jspx_eval_c_if_8 = _jspx_th_c_if_8.doStartTag();
        if (_jspx_eval_c_if_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t\t<div class=\"alert alert-info\">\n\t\t\t\t\t");
          if (_jspx_meth_liferay$1ui_message_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_8, _jspx_page_context))
            return;
          out.write("\n\t\t\t\t</div>\n\t\t\t");
        }
        if (_jspx_th_c_if_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
          return;
        }
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
        out.write("\n\t\t</div>\n\n\t\t");
        //  aui:button-row
        com.liferay.taglib.aui.ButtonRowTag _jspx_th_aui_button$1row_0 = (com.liferay.taglib.aui.ButtonRowTag) _jspx_tagPool_aui_button$1row_cssClass.get(com.liferay.taglib.aui.ButtonRowTag.class);
        _jspx_th_aui_button$1row_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_button$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
        _jspx_th_aui_button$1row_0.setCssClass("blog-article-button-row");
        int _jspx_eval_aui_button$1row_0 = _jspx_th_aui_button$1row_0.doStartTag();
        if (_jspx_eval_aui_button$1row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\n\t\t\t");

			String saveButtonLabel = "save";

			if ((entry == null) || entry.isDraft() || entry.isApproved()) {
				saveButtonLabel = "save-as-draft";
			}

			String publishButtonLabel = "publish";

			if (WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(themeDisplay.getCompanyId(), scopeGroupId, BlogsEntry.class.getName())) {
				publishButtonLabel = "submit-for-publication";
			}
			
          out.write("\n\n\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_9 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_9.setPageContext(_jspx_page_context);
          _jspx_th_c_if_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_button$1row_0);
          _jspx_th_c_if_9.setTest( (entry != null) && entry.isApproved() && WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(entry.getCompanyId(), entry.getGroupId(), BlogsEntry.class.getName()) );
          int _jspx_eval_c_if_9 = _jspx_th_c_if_9.doStartTag();
          if (_jspx_eval_c_if_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t<div class=\"alert alert-info\">\n\t\t\t\t\t");
            //  liferay-ui:message
            com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_10 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
            _jspx_th_liferay$1ui_message_10.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_message_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
            _jspx_th_liferay$1ui_message_10.setArguments( ResourceActionsUtil.getModelResource(locale, BlogsEntry.class.getName()) );
            _jspx_th_liferay$1ui_message_10.setKey("this-x-is-approved.-publishing-these-changes-will-cause-it-to-be-unpublished-and-go-through-the-approval-process-again");
            _jspx_th_liferay$1ui_message_10.setTranslateArguments( false );
            int _jspx_eval_liferay$1ui_message_10 = _jspx_th_liferay$1ui_message_10.doStartTag();
            if (_jspx_th_liferay$1ui_message_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_10);
              return;
            }
            _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_10);
            out.write("\n\t\t\t\t</div>\n\t\t\t");
          }
          if (_jspx_th_c_if_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
          out.write("\n\n\t\t\t");
          //  aui:button
          com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_0 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_type_name_disabled_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
          _jspx_th_aui_button_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_button_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_button$1row_0);
          _jspx_th_aui_button_0.setDisabled( pending );
          _jspx_th_aui_button_0.setName("publishButton");
          _jspx_th_aui_button_0.setType("submit");
          _jspx_th_aui_button_0.setValue( publishButtonLabel );
          int _jspx_eval_aui_button_0 = _jspx_th_aui_button_0.doStartTag();
          if (_jspx_th_aui_button_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_button_value_type_name_disabled_nobody.reuse(_jspx_th_aui_button_0);
            return;
          }
          _jspx_tagPool_aui_button_value_type_name_disabled_nobody.reuse(_jspx_th_aui_button_0);
          out.write("\n\n\t\t\t");
          //  aui:button
          com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_1 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_type_primary_name_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
          _jspx_th_aui_button_1.setPageContext(_jspx_page_context);
          _jspx_th_aui_button_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_button$1row_0);
          _jspx_th_aui_button_1.setName("saveButton");
          _jspx_th_aui_button_1.setPrimary( false );
          _jspx_th_aui_button_1.setType("submit");
          _jspx_th_aui_button_1.setValue( saveButtonLabel );
          int _jspx_eval_aui_button_1 = _jspx_th_aui_button_1.doStartTag();
          if (_jspx_th_aui_button_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_button_value_type_primary_name_nobody.reuse(_jspx_th_aui_button_1);
            return;
          }
          _jspx_tagPool_aui_button_value_type_primary_name_nobody.reuse(_jspx_th_aui_button_1);
          out.write("\n\n\t\t\t");
          //  aui:button
          com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_2 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_type_name_href_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
          _jspx_th_aui_button_2.setPageContext(_jspx_page_context);
          _jspx_th_aui_button_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_button$1row_0);
          _jspx_th_aui_button_2.setHref( redirect );
          _jspx_th_aui_button_2.setName("cancelButton");
          _jspx_th_aui_button_2.setType("cancel");
          int _jspx_eval_aui_button_2 = _jspx_th_aui_button_2.doStartTag();
          if (_jspx_th_aui_button_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_button_type_name_href_nobody.reuse(_jspx_th_aui_button_2);
            return;
          }
          _jspx_tagPool_aui_button_type_name_href_nobody.reuse(_jspx_th_aui_button_2);
          out.write("\n\t\t");
        }
        if (_jspx_th_aui_button$1row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_button$1row_cssClass.reuse(_jspx_th_aui_button$1row_0);
          return;
        }
        _jspx_tagPool_aui_button$1row_cssClass.reuse(_jspx_th_aui_button$1row_0);
        out.write('\n');
        out.write('	');
      }
      if (_jspx_th_aui_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_form_onSubmit_name_method_enctype_cssClass_action.reuse(_jspx_th_aui_form_0);
        return;
      }
      _jspx_tagPool_aui_form_onSubmit_name_method_enctype_cssClass_action.reuse(_jspx_th_aui_form_0);
      out.write("\n</div>\n\n");
      //  portlet:actionURL
      com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_3 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_windowState_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
      _jspx_th_portlet_actionURL_3.setPageContext(_jspx_page_context);
      _jspx_th_portlet_actionURL_3.setParent(null);
      _jspx_th_portlet_actionURL_3.setName("/blogs/edit_entry");
      _jspx_th_portlet_actionURL_3.setVar("editEntryURL");
      _jspx_th_portlet_actionURL_3.setWindowState( LiferayWindowState.EXCLUSIVE.toString() );
      int _jspx_eval_portlet_actionURL_3 = _jspx_th_portlet_actionURL_3.doStartTag();
      if (_jspx_eval_portlet_actionURL_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        //  portlet:param
        com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
        _jspx_th_portlet_param_0.setPageContext(_jspx_page_context);
        _jspx_th_portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_3);
        _jspx_th_portlet_param_0.setName("ajax");
        _jspx_th_portlet_param_0.setValue( Boolean.TRUE.toString() );
        int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
        if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
          return;
        }
        _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
        out.write('\n');
      }
      if (_jspx_th_portlet_actionURL_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_portlet_actionURL_windowState_var_name.reuse(_jspx_th_portlet_actionURL_3);
        return;
      }
      _jspx_tagPool_portlet_actionURL_windowState_var_name.reuse(_jspx_th_portlet_actionURL_3);
      editEntryURL = (java.lang.String) _jspx_page_context.findAttribute("editEntryURL");
      out.write('\n');
      out.write('\n');
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
          out.write("\n\tfunction ");
          if (_jspx_meth_portlet_namespace_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("OnChangeEditor(html) {\n\t\tvar blogs = Liferay.component('");
          if (_jspx_meth_portlet_namespace_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("Blogs');\n\n\t\tif (blogs) {\n\t\t\tblogs.setDescription(html);\n\t\t}\n\t}\n\n\tfunction ");
          if (_jspx_meth_portlet_namespace_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("OnChangeTitle(title) {\n\t\tvar blogs = Liferay.component('");
          if (_jspx_meth_portlet_namespace_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("Blogs');\n\n\t\tif (blogs) {\n\t\t\tblogs.updateFriendlyURL(title);\n\t\t}\n\t}\n\n\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_10 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_10.setPageContext(_jspx_page_context);
          _jspx_th_c_if_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
          _jspx_th_c_if_10.setTest( (entry != null) && blogsGroupServiceSettings.isEmailEntryUpdatedEnabled() );
          int _jspx_eval_c_if_10 = _jspx_th_c_if_10.doStartTag();
          if (_jspx_eval_c_if_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\tLiferay.Util.toggleBoxes('");
            if (_jspx_meth_portlet_namespace_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_10, _jspx_page_context))
              return;
            out.write("sendEmailEntryUpdated', '");
            if (_jspx_meth_portlet_namespace_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_10, _jspx_page_context))
              return;
            out.write("emailEntryUpdatedCommentWrapper');\n\t");
          }
          if (_jspx_th_c_if_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_10);
          out.write('\n');
          int evalDoAfterBody = _jspx_th_aui_script_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_aui_script_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_0);
        return;
      }
      _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_0);
      out.write('\n');
      out.write('\n');
      //  aui:script
      com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_1 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_use.get(com.liferay.taglib.aui.ScriptTag.class);
      _jspx_th_aui_script_1.setPageContext(_jspx_page_context);
      _jspx_th_aui_script_1.setParent(null);
      _jspx_th_aui_script_1.setUse("liferay-blogs");
      int _jspx_eval_aui_script_1 = _jspx_th_aui_script_1.doStartTag();
      if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_aui_script_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_aui_script_1.doInitBody();
        }
        do {
          out.write("\n\tvar blogs = Liferay.component(\n\t\t'");
          if (_jspx_meth_portlet_namespace_11((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.write("Blogs',\n\t\tnew Liferay.Blogs(\n\t\t\t{\n\t\t\t\tconstants: {\n\t\t\t\t\t'ACTION_PUBLISH': '");
          out.print( WorkflowConstants.ACTION_PUBLISH );
          out.write("',\n\t\t\t\t\t'ACTION_SAVE_DRAFT': '");
          out.print( WorkflowConstants.ACTION_SAVE_DRAFT );
          out.write("',\n\t\t\t\t\t'ADD': '");
          out.print( Constants.ADD );
          out.write("',\n\t\t\t\t\t'CMD': '");
          out.print( Constants.CMD );
          out.write("',\n\t\t\t\t\t'STATUS_DRAFT': '");
          out.print( WorkflowConstants.STATUS_DRAFT );
          out.write("',\n\t\t\t\t\t'UPDATE': '");
          out.print( Constants.UPDATE );
          out.write("'\n\t\t\t\t},\n\t\t\t\tdescriptionLength: '");
          out.print( pageAbstractLength );
          out.write("',\n\t\t\t\teditEntryURL: '");
          out.print( editEntryURL );
          out.write("',\n\n\t\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_11 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_11.setPageContext(_jspx_page_context);
          _jspx_th_c_if_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
          _jspx_th_c_if_11.setTest( entry != null );
          int _jspx_eval_c_if_11 = _jspx_th_c_if_11.doStartTag();
          if (_jspx_eval_c_if_11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\tentry: {\n\t\t\t\t\t\tcontent: '");
            out.print( UnicodeFormatter.toString(content) );
            out.write("',\n\t\t\t\t\t\tcustomDescription: ");
            out.print( customAbstract );
            out.write(",\n\t\t\t\t\t\tdescription: '");
            out.print( UnicodeFormatter.toString(description) );
            out.write("',\n\t\t\t\t\t\tpending: ");
            out.print( entry.isPending() );
            out.write(",\n\t\t\t\t\t\tstatus: '");
            out.print( entry.getStatus() );
            out.write("',\n\t\t\t\t\t\tsubtitle: '");
            out.print( UnicodeFormatter.toString(subtitle) );
            out.write("',\n\t\t\t\t\t\ttitle: '");
            out.print( UnicodeFormatter.toString(title) );
            out.write("',\n\t\t\t\t\t\tuserId: '");
            out.print( entry.getUserId() );
            out.write("'\n\t\t\t\t\t},\n\t\t\t\t");
          }
          if (_jspx_th_c_if_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_11);
          out.write("\n\n\t\t\t\tnamespace: '");
          if (_jspx_meth_portlet_namespace_12((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_1, _jspx_page_context))
            return;
          out.write("'\n\t\t\t}\n\t\t)\n\t);\n\n\tvar clearSaveDraftHandle = function(event) {\n\t\tif (event.portletId === '");
          out.print( portletDisplay.getRootPortletId() );
          out.write("') {\n\t\t\tblogs.destroy();\n\n\t\t\tLiferay.detach('destroyPortlet', clearSaveDraftHandle);\n\t\t}\n\t};\n\n\tLiferay.on('destroyPortlet', clearSaveDraftHandle);\n");
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
      out.write('\n');
      out.write('\n');

if (entry != null) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("mvcRenderCommandName", "/blogs/view_entry");
	portletURL.setParameter("entryId", String.valueOf(entry.getEntryId()));

	PortalUtil.addPortletBreadcrumbEntry(request, BlogsEntryUtil.getDisplayTitle(resourceBundle, entry), portletURL.toString());
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "edit"), currentURL);
}
else {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "add-entry"), currentURL);
}

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

  private boolean _jspx_meth_portlet_namespace_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_0 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
    int _jspx_eval_portlet_namespace_0 = _jspx_th_portlet_namespace_0.doStartTag();
    if (_jspx_th_portlet_namespace_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
    return false;
  }

  private boolean _jspx_meth_c_otherwise_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_choose_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
    _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
    _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
    int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
    if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\n\t\t\t<small class=\"text-capitalize text-muted\" id=\"");
      if (_jspx_meth_portlet_namespace_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_0, _jspx_page_context))
        return true;
      out.write("saveStatus\"></small>\n\t\t");
    }
    if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
      return true;
    }
    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_1 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_1.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
    int _jspx_eval_portlet_namespace_1 = _jspx_th_portlet_namespace_1.doStartTag();
    if (_jspx_th_portlet_namespace_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_1);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1asset_asset$1categories$1error_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-asset:asset-categories-error
    com.liferay.asset.taglib.servlet.taglib.AssetCategoriesErrorTag _jspx_th_liferay$1asset_asset$1categories$1error_0 = (com.liferay.asset.taglib.servlet.taglib.AssetCategoriesErrorTag) _jspx_tagPool_liferay$1asset_asset$1categories$1error_nobody.get(com.liferay.asset.taglib.servlet.taglib.AssetCategoriesErrorTag.class);
    _jspx_th_liferay$1asset_asset$1categories$1error_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1asset_asset$1categories$1error_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
    int _jspx_eval_liferay$1asset_asset$1categories$1error_0 = _jspx_th_liferay$1asset_asset$1categories$1error_0.doStartTag();
    if (_jspx_th_liferay$1asset_asset$1categories$1error_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1asset_asset$1categories$1error_nobody.reuse(_jspx_th_liferay$1asset_asset$1categories$1error_0);
      return true;
    }
    _jspx_tagPool_liferay$1asset_asset$1categories$1error_nobody.reuse(_jspx_th_liferay$1asset_asset$1categories$1error_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1asset_asset$1tags$1error_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-asset:asset-tags-error
    com.liferay.asset.taglib.servlet.taglib.AssetTagsErrorTag _jspx_th_liferay$1asset_asset$1tags$1error_0 = (com.liferay.asset.taglib.servlet.taglib.AssetTagsErrorTag) _jspx_tagPool_liferay$1asset_asset$1tags$1error_nobody.get(com.liferay.asset.taglib.servlet.taglib.AssetTagsErrorTag.class);
    _jspx_th_liferay$1asset_asset$1tags$1error_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1asset_asset$1tags$1error_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_form_0);
    int _jspx_eval_liferay$1asset_asset$1tags$1error_0 = _jspx_th_liferay$1asset_asset$1tags$1error_0.doStartTag();
    if (_jspx_th_liferay$1asset_asset$1tags$1error_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1asset_asset$1tags$1error_nobody.reuse(_jspx_th_liferay$1asset_asset$1tags$1error_0);
      return true;
    }
    _jspx_tagPool_liferay$1asset_asset$1tags$1error_nobody.reuse(_jspx_th_liferay$1asset_asset$1tags$1error_0);
    return false;
  }

  private boolean _jspx_meth_aui_input_5(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_5 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_5.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
    _jspx_th_aui_input_5.setName("coverImageCaption");
    _jspx_th_aui_input_5.setType("hidden");
    int _jspx_eval_aui_input_5 = _jspx_th_aui_input_5.doStartTag();
    if (_jspx_th_aui_input_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_5);
      return true;
    }
    _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_5);
    return false;
  }

  private boolean _jspx_meth_aui_input_6(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_6 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_6.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
    _jspx_th_aui_input_6.setName("title");
    _jspx_th_aui_input_6.setType("hidden");
    int _jspx_eval_aui_input_6 = _jspx_th_aui_input_6.doStartTag();
    if (_jspx_th_aui_input_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_6);
      return true;
    }
    _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_6);
    return false;
  }

  private boolean _jspx_meth_aui_input_7(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_7 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_7.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
    _jspx_th_aui_input_7.setName("subtitle");
    _jspx_th_aui_input_7.setType("hidden");
    int _jspx_eval_aui_input_7 = _jspx_th_aui_input_7.doStartTag();
    if (_jspx_th_aui_input_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_7);
      return true;
    }
    _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_7);
    return false;
  }

  private boolean _jspx_meth_aui_validator_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_input$1editor_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:validator
    com.liferay.taglib.aui.ValidatorTagImpl _jspx_th_aui_validator_0 = (com.liferay.taglib.aui.ValidatorTagImpl) _jspx_tagPool_aui_validator_name_nobody.get(com.liferay.taglib.aui.ValidatorTagImpl.class);
    _jspx_th_aui_validator_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_validator_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_input$1editor_3);
    _jspx_th_aui_validator_0.setName("required");
    int _jspx_eval_aui_validator_0 = _jspx_th_aui_validator_0.doStartTag();
    if (_jspx_th_aui_validator_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_validator_name_nobody.reuse(_jspx_th_aui_validator_0);
      return true;
    }
    _jspx_tagPool_aui_validator_name_nobody.reuse(_jspx_th_aui_validator_0);
    return false;
  }

  private boolean _jspx_meth_aui_input_8(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_8 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_8.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
    _jspx_th_aui_input_8.setName("content");
    _jspx_th_aui_input_8.setType("hidden");
    int _jspx_eval_aui_input_8 = _jspx_th_aui_input_8.doStartTag();
    if (_jspx_th_aui_input_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_8);
      return true;
    }
    _jspx_tagPool_aui_input_type_name_nobody.reuse(_jspx_th_aui_input_8);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_4(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_4 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_4.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
    _jspx_th_liferay$1ui_message_4.setKey("url");
    int _jspx_eval_liferay$1ui_message_4 = _jspx_th_liferay$1ui_message_4.doStartTag();
    if (_jspx_th_liferay$1ui_message_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_2(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_2 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_2.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
    int _jspx_eval_portlet_namespace_2 = _jspx_th_portlet_namespace_2.doStartTag();
    if (_jspx_th_portlet_namespace_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_2);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_2);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_5(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_5 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_5.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
    _jspx_th_liferay$1ui_message_5.setKey("abstract");
    int _jspx_eval_liferay$1ui_message_5 = _jspx_th_liferay$1ui_message_5.doStartTag();
    if (_jspx_th_liferay$1ui_message_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_icon$1help_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:icon-help
    com.liferay.taglib.ui.IconHelpTag _jspx_th_liferay$1ui_icon$1help_0 = (com.liferay.taglib.ui.IconHelpTag) _jspx_tagPool_liferay$1ui_icon$1help_message_nobody.get(com.liferay.taglib.ui.IconHelpTag.class);
    _jspx_th_liferay$1ui_icon$1help_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_icon$1help_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
    _jspx_th_liferay$1ui_icon$1help_0.setMessage("an-abstract-is-a-brief-summary-of-a-blog-entry");
    int _jspx_eval_liferay$1ui_icon$1help_0 = _jspx_th_liferay$1ui_icon$1help_0.doStartTag();
    if (_jspx_th_liferay$1ui_icon$1help_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_icon$1help_message_nobody.reuse(_jspx_th_liferay$1ui_icon$1help_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_icon$1help_message_nobody.reuse(_jspx_th_liferay$1ui_icon$1help_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_6(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_error_9, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_6 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_6.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_9);
    _jspx_th_liferay$1ui_message_6.setKey("image-names-must-end-with-one-of-the-following-extensions");
    int _jspx_eval_liferay$1ui_message_6 = _jspx_th_liferay$1ui_message_6.doStartTag();
    if (_jspx_th_liferay$1ui_message_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_6);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_6);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_7(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_error_10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_7 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_7.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_error_10);
    _jspx_th_liferay$1ui_message_7.setKey("an-error-occurred-while-scaling-the-abstract-image");
    int _jspx_eval_liferay$1ui_message_7 = _jspx_th_liferay$1ui_message_7.doStartTag();
    if (_jspx_th_liferay$1ui_message_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_7);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_7);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_3(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_3 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_3.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
    int _jspx_eval_portlet_namespace_3 = _jspx_th_portlet_namespace_3.doStartTag();
    if (_jspx_th_portlet_namespace_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_3);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_3);
    return false;
  }

  private boolean _jspx_meth_aui_validator_1(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_input_14, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:validator
    com.liferay.taglib.aui.ValidatorTagImpl _jspx_th_aui_validator_1 = (com.liferay.taglib.aui.ValidatorTagImpl) _jspx_tagPool_aui_validator_name_nobody.get(com.liferay.taglib.aui.ValidatorTagImpl.class);
    _jspx_th_aui_validator_1.setPageContext(_jspx_page_context);
    _jspx_th_aui_validator_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_input_14);
    _jspx_th_aui_validator_1.setName("required");
    int _jspx_eval_aui_validator_1 = _jspx_th_aui_validator_1.doStartTag();
    if (_jspx_th_aui_validator_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_validator_name_nobody.reuse(_jspx_th_aui_validator_1);
      return true;
    }
    _jspx_tagPool_aui_validator_name_nobody.reuse(_jspx_th_aui_validator_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_8(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_8 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_8.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
    _jspx_th_liferay$1ui_message_8.setKey("small-image");
    int _jspx_eval_liferay$1ui_message_8 = _jspx_th_liferay$1ui_message_8.doStartTag();
    if (_jspx_th_liferay$1ui_message_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_8);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_8);
    return false;
  }

  private boolean _jspx_meth_aui_input_15(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_fieldset_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_15 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_15.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_3);
    _jspx_th_aui_input_15.setLabel("display-date");
    _jspx_th_aui_input_15.setName("displayDate");
    int _jspx_eval_aui_input_15 = _jspx_th_aui_input_15.doStartTag();
    if (_jspx_th_aui_input_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_15);
      return true;
    }
    _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_15);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_4 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_4.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
    int _jspx_eval_portlet_namespace_4 = _jspx_th_portlet_namespace_4.doStartTag();
    if (_jspx_th_portlet_namespace_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_4);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_4);
    return false;
  }

  private boolean _jspx_meth_aui_input_20(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:input
    com.liferay.taglib.aui.InputTag _jspx_th_aui_input_20 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_name_label_nobody.get(com.liferay.taglib.aui.InputTag.class);
    _jspx_th_aui_input_20.setPageContext(_jspx_page_context);
    _jspx_th_aui_input_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
    _jspx_th_aui_input_20.setLabel("trackbacks-to-send");
    _jspx_th_aui_input_20.setName("trackbacks");
    int _jspx_eval_aui_input_20 = _jspx_th_aui_input_20.doStartTag();
    if (_jspx_th_aui_input_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_20);
      return true;
    }
    _jspx_tagPool_aui_input_name_label_nobody.reuse(_jspx_th_aui_input_20);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_9(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_9 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_9.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_8);
    _jspx_th_liferay$1ui_message_9.setKey("there-is-a-publication-workflow-in-process");
    int _jspx_eval_liferay$1ui_message_9 = _jspx_th_liferay$1ui_message_9.doStartTag();
    if (_jspx_th_liferay$1ui_message_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_9);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_9);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_5(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_5 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_5.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_5 = _jspx_th_portlet_namespace_5.doStartTag();
    if (_jspx_th_portlet_namespace_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_5);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_5);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_6(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_6 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_6.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_6 = _jspx_th_portlet_namespace_6.doStartTag();
    if (_jspx_th_portlet_namespace_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_6);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_6);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_7(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_7 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_7.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_7 = _jspx_th_portlet_namespace_7.doStartTag();
    if (_jspx_th_portlet_namespace_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_7);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_7);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_8(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_8 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_8.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_8 = _jspx_th_portlet_namespace_8.doStartTag();
    if (_jspx_th_portlet_namespace_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_8);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_8);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_9(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_9 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_9.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
    int _jspx_eval_portlet_namespace_9 = _jspx_th_portlet_namespace_9.doStartTag();
    if (_jspx_th_portlet_namespace_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_9);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_9);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_10(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_10 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_10.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_10);
    int _jspx_eval_portlet_namespace_10 = _jspx_th_portlet_namespace_10.doStartTag();
    if (_jspx_th_portlet_namespace_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_10);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_10);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_11(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_11 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_11.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_11 = _jspx_th_portlet_namespace_11.doStartTag();
    if (_jspx_th_portlet_namespace_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_11);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_11);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_12(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_12 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_12.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_1);
    int _jspx_eval_portlet_namespace_12 = _jspx_th_portlet_namespace_12.doStartTag();
    if (_jspx_th_portlet_namespace_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_12);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_12);
    return false;
  }
}
