package de.saxsys.javafx.migration;

public class Pointcuts {
	public static final String SWINGNODE_SETCONTENT = "call (* javafx.embed.swing.SwingNode.setContent(..))";
	public static final String JFXPANEL_INIT = "call (javafx.embed.swing.JFXPanel*+.new(..))";
	public static final String JFXPANEL_ANYMETHOD = "call (* javafx.embed.swing.JFXPanel*+.*(..))";
	
}
