package com.bigdatan.framework.ueditor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;

import com.bigdatan.framework.ueditor.define.ActionMap;
import com.bigdatan.framework.ueditor.define.AppInfo;
import com.bigdatan.framework.ueditor.define.BaseState;
import com.bigdatan.framework.ueditor.define.State;
import com.bigdatan.framework.ueditor.hunter.FileManager;
import com.bigdatan.framework.ueditor.hunter.ImageHunter;
import com.bigdatan.framework.ueditor.upload.Uploader;

public class ActionEnter {
	
	private ConfigManager configManager = null;
	
	private HttpServletRequest request = null;
	
	private String actionType = null;
	
	public ActionEnter(ConfigManager configManager){
		this.configManager = configManager;
	}
	
	public String exec (HttpServletRequest request) {
		
		this.request = request;
		this.actionType = request.getParameter( "action" );
		String callbackName = this.request.getParameter("callback");
		
		if ( callbackName != null ) {

			if ( !validCallbackName( callbackName ) ) {
				return new BaseState( false, AppInfo.ILLEGAL ).toJSONString();
			}
			
			return callbackName+"("+this.invoke()+");";
			
		} else {
			return this.invoke();
		}

	}
	
	public String invoke() {
		
		State state = null;
		try {
			if ( actionType == null || !ActionMap.mapping.containsKey( actionType ) ) {
				return new BaseState( false, AppInfo.INVALID_ACTION ).toJSONString();
			}
			
			if ( this.configManager == null || !this.configManager.valid() ) {
				return new BaseState( false, AppInfo.CONFIG_ERROR ).toJSONString();
			}
			
			int actionCode = ActionMap.getType( this.actionType );
			
			Map<String, Object> conf = null;
			
			switch ( actionCode ) {
			
				case ActionMap.CONFIG:
					return this.configManager.getAllConfig().toString();
					
				case ActionMap.UPLOAD_IMAGE:
				case ActionMap.UPLOAD_SCRAWL:
				case ActionMap.UPLOAD_VIDEO:
				case ActionMap.UPLOAD_FILE:
					conf = this.configManager.getConfig( actionCode );
					state = new Uploader( request, conf ).doExec();
					break;
					
				case ActionMap.CATCH_IMAGE:
					conf = configManager.getConfig( actionCode );
					String[] list = this.request.getParameterValues( (String)conf.get( "fieldName" ) );
					state = new ImageHunter( conf ).capture( list );
					break;
					
				case ActionMap.LIST_IMAGE:
				case ActionMap.LIST_FILE:
					conf = configManager.getConfig( actionCode );
					int start = this.getStartIndex();
					String marker = this.getMarker();
					state = new FileManager( conf ).listFile( start,marker );
					break;
					
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return state.toJSONString();
		
	}
	
	public int getStartIndex () {
		
		String start = this.request.getParameter( "start" );
		try {
			return Integer.parseInt( start );
		} catch ( Exception e ) {
			return 0;
		}
		
	}
	public String getMarker () {
		
		String marker = this.request.getParameter( "marker" );
		return marker;
	}
	
	/**
	 * callback参数验证
	 */
	public boolean validCallbackName ( String name ) {
		
		if ( name.matches( "^[a-zA-Z_]+[\\w0-9_]*$" ) ) {
			return true;
		}
		
		return false;
		
	}
	
}
