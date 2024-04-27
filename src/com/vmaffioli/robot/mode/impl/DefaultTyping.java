package com.vmaffioli.robot.mode.impl;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.vmaffioli.robot.mode.ITyping;

public class DefaultTyping implements ITyping {

	@Override
	public void execute(Robot robot, String text) {

		for (char c : text.toCharArray()) {
			int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);

			if (keyCode != KeyEvent.VK_UNDEFINED) {
				switch (c) {
				case '~':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_BACK_QUOTE);
					robot.keyRelease(KeyEvent.VK_BACK_QUOTE);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case '`':
				case '¨':
					robot.keyPress(KeyEvent.VK_BACK_QUOTE);
					robot.keyRelease(KeyEvent.VK_BACK_QUOTE);
					break;
				case '_':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_MINUS);
					robot.keyRelease(KeyEvent.VK_MINUS);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case ':':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_SEMICOLON);
					robot.keyRelease(KeyEvent.VK_SEMICOLON);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case '/':
					robot.keyPress(KeyEvent.VK_DIVIDE);
					robot.keyRelease(KeyEvent.VK_DIVIDE);
					break;
				case 'A':
				case 'B':
				case 'C':
				case 'D':
				case 'E':
				case 'F':
				case 'G':
				case 'H':
				case 'I':
				case 'J':
				case 'K':
				case 'L':
				case 'M':
				case 'N':
				case 'O':
				case 'P':
				case 'Q':
				case 'R':
				case 'S':
				case 'T':
				case 'U':
				case 'V':
				case 'W':
				case 'X':
				case 'Y':
				case 'Z':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(keyCode);
					robot.keyRelease(keyCode);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'a':
				case 'b':
				case 'c':
				case 'd':
				case 'e':
				case 'f':
				case 'g':
				case 'h':
				case 'i':
				case 'j':
				case 'k':
				case 'l':
				case 'm':
				case 'n':
				case 'o':
				case 'p':
				case 'q':
				case 'r':
				case 's':
				case 't':
				case 'u':
				case 'v':
				case 'w':
				case 'x':
				case 'y':
				case 'z':
					robot.keyPress(keyCode);
					robot.keyRelease(keyCode);
					break;
				case 'À':
				case 'Á':
				case 'Â':
				case 'Ã':
				case 'Ç':
				case 'É':
				case 'Ê':
				case 'Í':
				case 'Ó':
				case 'Ô':
				case 'Õ':
				case 'Ú':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(keyCode);
					robot.keyRelease(keyCode);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'à':
				case 'á':
				case 'â':
				case 'ã':
				case 'ç':
				case 'é':
				case 'ê':
				case 'í':
				case 'ó':
				case 'ô':
				case 'õ':
				case 'ú':
					robot.keyPress(keyCode);
					robot.keyRelease(keyCode);
					break;
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
				case '!':
				case '@':
				case '#':
				case '$':
				case '%':
				case '^':
				case '&':
				case '*':
				case '(':
				case ')':
				case '-':
				case '=':
				case '+':
				case '[':
				case ']':
				case '{':
				case '}':
				case '|':
				case '\\':
				case ';':
				case '\'':
				case '"':
				case ',':
				case '.':
				case '<':
				case '>':
				case '?':
					robot.keyPress(keyCode);
					robot.keyRelease(keyCode);
					break;
				default:
					System.out.println("Caractere não suportado: " + c);
					break;
				}
			} else {
				System.out.println("Caractere não suportado: " + c);
			}
		}

	}

}
