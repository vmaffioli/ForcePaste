package com.vmaffioli.robot;

import java.awt.AWTException;
import java.awt.Robot;

import com.vmaffioli.enums.TypingEnum;
import com.vmaffioli.robot.mode.ITyping;
import com.vmaffioli.robot.mode.impl.DefaultTyping;

public class RobotTyping {

	private Robot robot;
	private TypingEnum mode;

	private ITyping typing;

	public RobotTyping() {
		try {
			this.robot = new Robot();
			this.mode = TypingEnum.DEFAULT;
			this.setMode(this.mode);

		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public void setMode(TypingEnum mode) {
		this.mode = mode;
		if (this.mode.equals(TypingEnum.ACCESSIBILITY)) {

		} else {
			this.typing = new DefaultTyping();
		}
	}

	public void execute(String text) {
		this.typing.execute(this.robot, text);
	}

}
