package net.ajmichael.util;

import java.io.IOException;

public class Postfix {
	
	public static void main(String[] args) throws IOException {
		Parser parse = new Parser();
		parse.expr();
		System.out.write('\n');
	}

}

class Parser {
	static int lookahead;
	
	public Parser() throws IOException {
		lookahead = System.in.read();
	}
	
	void expr() throws IOException {
		term();
		while(true) {
			if (lookahead == '+' || lookahead == '-') {
				match(lookahead);
				term();
				System.out.write(lookahead);
			} else {
				return;
			}
		}
	}
	
	void term() throws IOException {
		if (Character.isDigit((char)lookahead)) {
			System.out.write((char)lookahead);
			match(lookahead);
		} else {
			throw new Error("syntax error");
		}
	}
	
	void match(int t) throws IOException {
		if (lookahead == t) {
			lookahead = System.in.read();
		} else {
			throw new Error("syntax error <insert helpful error message here");
		}
	}	
	
}