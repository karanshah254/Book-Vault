package com.book.customTag;

import java.io.IOException;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class BookActionTag extends TagSupport {
	private String action;

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();

			switch (action.toLowerCase()) {
				case "add":
					out.print("<button onclick='alert(\"Add Book clicked\")'>Add Book</button>");
					break;
				case "view":
					out.print("<button onclick='alert(\"View Books clicked\")'>View Books</button>");
					break;
				case "delete":
					out.print("<button onclick='alert(\"Delete Book clicked\")'>Delete Book</button>");
					break;
				case "modify":
					out.print("<button onclick='alert(\"Modify Book clicked\")'>Modify Book</button>");
					break;
				default:
					out.print("<p>Invalid Action</p>");
			}
		} catch (IOException e) {
			throw new JspException("Error in BookActionTag", e);
		}
		return SKIP_BODY;
	}
}
