package net.ajmichael.lexer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Lexer {

	public int line = 1;
	private char peek = ' ';
	private Map<String, Word> words = new HashMap<>();
	
	void reserve(Word t) {
		words.put(t.lexeme, t);
	}
	
	public Token scan() throws IOException {
		for (;; peek = (char)System.in.read()) {
			if (peek == '\n') {
				line = line + 1;
			} else if (peek != ' ' && peek != '\t') {
				break;
			}
		}
		if (Character.isDigit(peek)) {
			int v = 0;
			do {
				v = 10*v + Character.digit(peek, 10);
				peek = (char) System.in.read();
			} while (Character.isDigit(peek));
			return new Num(v);
		} else if (Character.isLetter(peek)) {
			StringBuffer b = new StringBuffer();
			do {
				b.append(peek);
				peek = (char) System.in.read();
			} while (Character.isLetterOrDigit(peek));
			String s = b.toString();
			Word w = (Word) words.get(s);
			if (w != null) {
				return w;
			} else {
				w = new Word(Tag.ID, s);
				words.put(s, w);
				return w;
			}
		} else {
			Token t = new Token(peek);
			peek = ' ';
			return t;
		}
	}
	
}