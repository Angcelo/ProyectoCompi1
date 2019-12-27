package analizadores;
import java_cup.runtime.Symbol; 
import proyectcompi1.ProyectCompi1;
import proyectcompi1.cError;


public class Lexico implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;

	String aux = "";
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public Lexico (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public Lexico (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Lexico () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

    yyline = 1; 
    yychar = 1; 
	}

	private boolean yy_eof_done = false;
	private final int ESTADO_CADENA = 3;
	private final int YYINITIAL = 0;
	private final int COMENTARIO_MULTI = 2;
	private final int COMENTARIO = 1;
	private final int yy_state_dtrans[] = {
		0,
		108,
		111,
		113
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NOT_ACCEPT,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NOT_ACCEPT,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NOT_ACCEPT,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NOT_ACCEPT,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NOT_ACCEPT,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NOT_ACCEPT,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NO_ANCHOR,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NO_ANCHOR,
		/* 154 */ YY_NO_ANCHOR,
		/* 155 */ YY_NO_ANCHOR,
		/* 156 */ YY_NO_ANCHOR,
		/* 157 */ YY_NO_ANCHOR,
		/* 158 */ YY_NO_ANCHOR,
		/* 159 */ YY_NO_ANCHOR,
		/* 160 */ YY_NO_ANCHOR,
		/* 161 */ YY_NO_ANCHOR,
		/* 162 */ YY_NO_ANCHOR,
		/* 163 */ YY_NO_ANCHOR,
		/* 164 */ YY_NO_ANCHOR,
		/* 165 */ YY_NO_ANCHOR,
		/* 166 */ YY_NO_ANCHOR,
		/* 167 */ YY_NO_ANCHOR,
		/* 168 */ YY_NO_ANCHOR,
		/* 169 */ YY_NO_ANCHOR,
		/* 170 */ YY_NO_ANCHOR,
		/* 171 */ YY_NO_ANCHOR,
		/* 172 */ YY_NO_ANCHOR,
		/* 173 */ YY_NO_ANCHOR,
		/* 174 */ YY_NO_ANCHOR,
		/* 175 */ YY_NO_ANCHOR,
		/* 176 */ YY_NO_ANCHOR,
		/* 177 */ YY_NO_ANCHOR,
		/* 178 */ YY_NO_ANCHOR,
		/* 179 */ YY_NO_ANCHOR,
		/* 180 */ YY_NO_ANCHOR,
		/* 181 */ YY_NO_ANCHOR,
		/* 182 */ YY_NO_ANCHOR,
		/* 183 */ YY_NO_ANCHOR,
		/* 184 */ YY_NO_ANCHOR,
		/* 185 */ YY_NO_ANCHOR,
		/* 186 */ YY_NO_ANCHOR,
		/* 187 */ YY_NO_ANCHOR,
		/* 188 */ YY_NO_ANCHOR,
		/* 189 */ YY_NO_ANCHOR,
		/* 190 */ YY_NO_ANCHOR,
		/* 191 */ YY_NO_ANCHOR,
		/* 192 */ YY_NO_ANCHOR,
		/* 193 */ YY_NO_ANCHOR,
		/* 194 */ YY_NO_ANCHOR,
		/* 195 */ YY_NO_ANCHOR,
		/* 196 */ YY_NO_ANCHOR,
		/* 197 */ YY_NO_ANCHOR,
		/* 198 */ YY_NO_ANCHOR,
		/* 199 */ YY_NO_ANCHOR,
		/* 200 */ YY_NO_ANCHOR,
		/* 201 */ YY_NO_ANCHOR,
		/* 202 */ YY_NO_ANCHOR,
		/* 203 */ YY_NO_ANCHOR,
		/* 204 */ YY_NO_ANCHOR,
		/* 205 */ YY_NO_ANCHOR,
		/* 206 */ YY_NO_ANCHOR,
		/* 207 */ YY_NO_ANCHOR,
		/* 208 */ YY_NO_ANCHOR,
		/* 209 */ YY_NO_ANCHOR,
		/* 210 */ YY_NO_ANCHOR,
		/* 211 */ YY_NO_ANCHOR,
		/* 212 */ YY_NO_ANCHOR,
		/* 213 */ YY_NO_ANCHOR,
		/* 214 */ YY_NO_ANCHOR,
		/* 215 */ YY_NO_ANCHOR,
		/* 216 */ YY_NO_ANCHOR,
		/* 217 */ YY_NO_ANCHOR,
		/* 218 */ YY_NO_ANCHOR,
		/* 219 */ YY_NO_ANCHOR,
		/* 220 */ YY_NO_ANCHOR,
		/* 221 */ YY_NO_ANCHOR,
		/* 222 */ YY_NO_ANCHOR,
		/* 223 */ YY_NO_ANCHOR,
		/* 224 */ YY_NO_ANCHOR,
		/* 225 */ YY_NO_ANCHOR,
		/* 226 */ YY_NO_ANCHOR,
		/* 227 */ YY_NO_ANCHOR,
		/* 228 */ YY_NO_ANCHOR,
		/* 229 */ YY_NO_ANCHOR,
		/* 230 */ YY_NO_ANCHOR,
		/* 231 */ YY_NO_ANCHOR,
		/* 232 */ YY_NO_ANCHOR,
		/* 233 */ YY_NO_ANCHOR,
		/* 234 */ YY_NO_ANCHOR,
		/* 235 */ YY_NO_ANCHOR,
		/* 236 */ YY_NO_ANCHOR,
		/* 237 */ YY_NO_ANCHOR,
		/* 238 */ YY_NO_ANCHOR,
		/* 239 */ YY_NO_ANCHOR,
		/* 240 */ YY_NO_ANCHOR,
		/* 241 */ YY_NO_ANCHOR,
		/* 242 */ YY_NO_ANCHOR,
		/* 243 */ YY_NO_ANCHOR,
		/* 244 */ YY_NO_ANCHOR,
		/* 245 */ YY_NO_ANCHOR,
		/* 246 */ YY_NO_ANCHOR,
		/* 247 */ YY_NO_ANCHOR,
		/* 248 */ YY_NO_ANCHOR,
		/* 249 */ YY_NO_ANCHOR,
		/* 250 */ YY_NO_ANCHOR,
		/* 251 */ YY_NO_ANCHOR,
		/* 252 */ YY_NO_ANCHOR,
		/* 253 */ YY_NO_ANCHOR,
		/* 254 */ YY_NO_ANCHOR,
		/* 255 */ YY_NO_ANCHOR,
		/* 256 */ YY_NO_ANCHOR,
		/* 257 */ YY_NO_ANCHOR,
		/* 258 */ YY_NO_ANCHOR,
		/* 259 */ YY_NO_ANCHOR,
		/* 260 */ YY_NO_ANCHOR,
		/* 261 */ YY_NO_ANCHOR,
		/* 262 */ YY_NO_ANCHOR,
		/* 263 */ YY_NO_ANCHOR,
		/* 264 */ YY_NO_ANCHOR,
		/* 265 */ YY_NO_ANCHOR,
		/* 266 */ YY_NO_ANCHOR,
		/* 267 */ YY_NO_ANCHOR,
		/* 268 */ YY_NO_ANCHOR,
		/* 269 */ YY_NO_ANCHOR,
		/* 270 */ YY_NO_ANCHOR,
		/* 271 */ YY_NO_ANCHOR,
		/* 272 */ YY_NO_ANCHOR,
		/* 273 */ YY_NO_ANCHOR,
		/* 274 */ YY_NO_ANCHOR,
		/* 275 */ YY_NO_ANCHOR,
		/* 276 */ YY_NO_ANCHOR,
		/* 277 */ YY_NO_ANCHOR,
		/* 278 */ YY_NO_ANCHOR,
		/* 279 */ YY_NO_ANCHOR,
		/* 280 */ YY_NO_ANCHOR,
		/* 281 */ YY_NO_ANCHOR,
		/* 282 */ YY_NO_ANCHOR,
		/* 283 */ YY_NO_ANCHOR,
		/* 284 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"4:9,5,1,4:2,2,4:18,5,16,7,4:2,10,18,58,11,12,6,8,20,9,28,3,57:10,24,21,14,1" +
"3,15,25,4,59:2,53,51,59:2,56,59,52,59:9,42,59:7,26,4,27,19,55,4,40,35,38,32" +
",37,48,43,39,29,59,50,36,54,30,33,47,59,41,44,31,34,46,49,45,59:2,22,17,23," +
"4:51,59,4:17,59,4:8020,59,4:57319,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,285,
"0,1:2,2,3,1,4,1,5,6,1:3,7,8,9,10,1:10,11,12,1:11,13,14,13:5,15,1,13:6,16,13" +
":6,17,13:26,1,18,1:2,19,1:4,20,21,22,23,15,24,25,26,20,27,28,29,30,31,32,33" +
",34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58" +
",59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83" +
",84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,10" +
"6,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,1" +
"25,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143," +
"144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162" +
",163,164,13,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180" +
",181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,19" +
"9,200,201,202,203")[0];

	private int yy_nxt[][] = unpackFromString(204,60,
"1,2,3,4,5,3,6,7,8,9,10,11,12,13,14,15,16,99,103,17,18,19,20,21,22,23,24,25," +
"26,27,100,218,104,245:2,260,245,266,269,245,272,273,274,275,276,245,277,278" +
",279,280,245:5,106,281,28,109,245,-1:62,3,-1:2,3,-1:57,29,-1:2,30,-1:59,31," +
"-1:61,32,-1:60,33,-1:63,34,-1:59,35,-1:59,36,-1:59,37,-1:75,245,107,245:17," +
"40,245:5,282,245:3,-1,245,-1:28,102,-1:28,28,-1:31,245:29,-1,245,-1:29,245:" +
"5,226,245:23,-1,245,-1:57,47,-1:31,245:26,174,245:2,-1,245,-1:29,245:2,187," +
"245:4,188,245:21,-1,245,-1:2,90,-1:2,90,-1:56,93,-1:2,93,-1:83,132:26,98,13" +
"2:2,-1,132,-1:17,38,-1:71,245:5,283,245:2,110,245:20,-1,245,-1:3,95,-1:74,3" +
"9,-1:70,245:4,41,245:3,116,245:20,-1,245,-1:58,48,-1:30,245:2,42,245:12,222" +
",245:13,-1,245,1,89,90,91:2,90,91:54,-1:3,105:57,-1:29,245:20,43,245:8,-1,2" +
"45,1,92,93,94:2,93,101,94:53,-1:29,245:22,247,249,134,245:4,-1,245,1,-1:2,9" +
"6:4,97,96:52,-1:29,135,245:28,-1,245,-1:29,245:5,136,245:23,-1,245,-1:29,24" +
"5:19,267,245:9,-1,245,-1:29,245:4,227,245:24,-1,245,-1:29,245:8,270,245:20," +
"-1,245,-1:29,245:15,137,245:13,-1,245,-1:29,245:2,138,245:26,-1,245,-1:29,2" +
"45,139,245:27,-1,245,-1:29,245:11,140,245:17,-1,245,-1:29,245:2,143,245:8,1" +
"44,245:17,-1,245,-1:29,245:12,145,245:16,-1,245,-1:29,245:11,253,44,245:16," +
"-1,245,-1:29,245:18,228,245:10,-1,245,-1:29,245:20,45,245:8,-1,245,-1:29,24" +
"5:6,251,245:22,-1,245,-1:29,147,245:3,268,245:24,-1,245,-1:29,245:12,46,245" +
":16,-1,245,-1:29,245:7,230,245:21,-1,245,-1:29,132:29,-1,132,-1:29,245:7,49" +
",245:21,-1,245,-1:29,245:10,255,245:18,-1,245,-1:29,245:15,50,245:13,-1,245" +
",-1:29,245:8,51,245:20,-1,245,-1:29,245:8,52,245:20,-1,245,-1:29,245:8,154," +
"245:20,-1,245,-1:29,245:2,232,245:26,-1,245,-1:29,245:15,155,245:13,-1,245," +
"-1:29,245:12,53,245:16,-1,245,-1:29,245:8,54,245:20,-1,245,-1:29,245:5,256," +
"245:23,-1,245,-1:29,245:3,55,245:25,-1,245,-1:29,236,245:28,-1,245,-1:29,24" +
"5:3,56,245:25,-1,245,-1:29,245,160,245:15,264,245:11,-1,245,-1:29,245:11,16" +
"2,245:17,-1,245,-1:29,245:7,164,245:21,-1,245,-1:29,245:19,258,245:9,-1,245" +
",-1:29,245:12,165,245:16,-1,245,-1:29,245:2,57,245:26,-1,245,-1:29,245:21,5" +
"8,245:7,-1,245,-1:29,245,171,245:27,-1,245,-1:29,245:15,59,245:13,-1,245,-1" +
":29,245:10,60,245:18,-1,245,-1:29,176,245:28,-1,245,-1:29,245:12,61,245:16," +
"-1,245,-1:29,245:9,177,245:19,-1,245,-1:29,245:2,62,245:26,-1,245,-1:29,245" +
":8,180,245:20,-1,245,-1:29,245:7,63,245:21,-1,245,-1:29,245:8,64,245:20,-1," +
"245,-1:29,245:8,65,245:20,-1,245,-1:29,245:2,66,245:26,-1,245,-1:29,245:6,2" +
"39,245:22,-1,245,-1:29,245:12,67,245:16,-1,245,-1:29,245:8,68,245:20,-1,245" +
",-1:29,245:7,182,245:21,-1,245,-1:29,245:11,183,245:17,-1,245,-1:29,245:3,1" +
"84,245:25,-1,245,-1:29,245,185,245:27,-1,245,-1:29,245,69,245:27,-1,245,-1:" +
"29,245:19,186,245:9,-1,245,-1:29,245:14,70,245:14,-1,245,-1:29,245:9,71,245" +
":19,-1,245,-1:29,245:10,72,245:18,-1,245,-1:29,245:9,73,245:19,-1,245,-1:29" +
",245:2,189,245:26,-1,245,-1:29,245:9,190,245:19,-1,245,-1:29,245:26,191,245" +
":2,-1,245,-1:29,245:2,74,245:26,-1,245,-1:29,245,75,245:27,-1,245,-1:29,245" +
":15,76,245:13,-1,245,-1:29,245:5,194,245:23,-1,245,-1:29,196,245:28,-1,245," +
"-1:29,245:11,197,245:17,-1,245,-1:29,245,77,245:27,-1,245,-1:29,245:8,78,24" +
"5:20,-1,245,-1:29,245:2,243,245:26,-1,245,-1:29,245:19,198,245:9,-1,245,-1:" +
"29,245:8,200,245:20,-1,245,-1:29,245:8,79,245:20,-1,245,-1:29,245:8,80,245:" +
"20,-1,245,-1:29,245:2,81,245:26,-1,245,-1:29,245:7,201,245:21,-1,245,-1:29," +
"245:6,202,245:22,-1,245,-1:29,244,245:28,-1,245,-1:29,245:12,204,245:16,-1," +
"245,-1:29,245:4,205,245:24,-1,245,-1:29,245:8,82,245:20,-1,245,-1:29,245:7," +
"206,245:21,-1,245,-1:29,245:3,83,245:25,-1,245,-1:29,245:26,208,245:2,-1,24" +
"5,-1:29,245:19,84,245:9,-1,245,-1:29,245:11,85,245:17,-1,245,-1:29,245:8,86" +
",245:20,-1,245,-1:29,245:3,209,245:4,210,245:20,-1,245,-1:29,245:4,211,245:" +
"24,-1,245,-1:29,245,212,245:27,-1,245,-1:29,245:2,87,245:26,-1,245,-1:29,24" +
"5:2,213,245:26,-1,245,-1:29,245:4,214,245:24,-1,245,-1:29,245:12,215,245:16" +
",-1,245,-1:29,245,216,245:27,-1,245,-1:29,245:4,217,245:24,-1,245,-1:29,245" +
":15,88,245:13,-1,245,-1:29,245:4,112,245:5,114,245,115,245:16,-1,245,-1:29," +
"263,245:28,-1,245,-1:29,245:4,151,245:24,-1,245,-1:29,245:15,142,245:13,-1," +
"245,-1:29,245:2,233,245:26,-1,245,-1:29,245,148,245:27,-1,245,-1:29,245:11," +
"141,245:17,-1,245,-1:29,245:18,156,245:10,-1,245,-1:29,245:6,234,245:22,-1," +
"245,-1:29,245:7,252,245:21,-1,245,-1:29,245:8,158,245:20,-1,245,-1:29,245:2" +
",235,245:26,-1,245,-1:29,245:15,163,245:13,-1,245,-1:29,245:5,166,245:23,-1" +
",245,-1:29,172,245:28,-1,245,-1:29,245:11,257,245:17,-1,245,-1:29,245:7,168" +
",245:21,-1,245,-1:29,245:12,240,245:16,-1,245,-1:29,245,175,245:27,-1,245,-" +
"1:29,178,245:28,-1,245,-1:29,245:8,181,245:20,-1,245,-1:29,245:7,193,245:21" +
",-1,245,-1:29,245:11,265,245:17,-1,245,-1:29,245:9,242,245:19,-1,245,-1:29," +
"245:11,199,245:17,-1,245,-1:29,245:8,203,245:20,-1,245,-1:29,245:7,207,245:" +
"21,-1,245,-1:29,146,245:28,-1,245,-1:29,245:4,231,245:24,-1,245,-1:29,245:1" +
"5,229,245:13,-1,245,-1:29,245,152,245:27,-1,245,-1:29,245:11,225,245:17,-1," +
"245,-1:29,245:7,237,245:21,-1,245,-1:29,245:8,170,245:20,-1,245,-1:29,245:2" +
",157,245:26,-1,245,-1:29,245:5,169,245:23,-1,245,-1:29,245:11,167,245:17,-1" +
",245,-1:29,245:12,173,245:16,-1,245,-1:29,245,259,245:27,-1,245,-1:29,241,2" +
"45:28,-1,245,-1:29,245:9,192,245:19,-1,245,-1:29,245:4,117,245:7,118,245:16" +
",-1,245,-1:29,149,245:28,-1,245,-1:29,245:11,150,245:17,-1,245,-1:29,245:2," +
"159,245:26,-1,245,-1:29,245:11,179,245:17,-1,245,-1:29,245:9,195,245:19,-1," +
"245,-1:29,245:7,119,245:8,120,245:12,-1,245,-1:29,245:11,254,245:17,-1,245," +
"-1:29,245:2,161,245:26,-1,245,-1:29,245:4,121,245:2,122,245:2,224,221,245:1" +
"7,-1,245,-1:29,245:11,153,245:17,-1,245,-1:29,245:2,238,245:26,-1,245,-1:29" +
",245:6,248,245:22,-1,245,-1:29,245:8,123,245:20,-1,245,-1:29,245:2,124,245:" +
"26,-1,245,-1:29,245:12,250,245:16,-1,245,-1:29,245:2,125,245:2,126,245:14,2" +
"19,245:8,-1,245,-1:29,245:4,246,245:24,-1,245,-1:29,245:4,127,128,245:6,129" +
",245:16,-1,245,-1:29,223,245:3,130,245:6,131,245:17,-1,245,-1:29,245:10,261" +
",245,284,245:16,-1,245,-1:29,245:12,262,245:16,-1,245,-1:29,245:18,220,245:" +
"10,-1,245,-1:29,245:7,133,245:21,-1,245,-1:29,271,245:28,-1,245");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{ yychar=1;}
					case -3:
						break;
					case 3:
						{}
					case -4:
						break;
					case 4:
						{return new Symbol(sym.division,yyline,yychar,yytext());}
					case -5:
						break;
					case 5:
						{
    cError error=new cError("Lexico",yytext(),yyline,yychar);
    ProyectCompi1.errores.add(error);
}
					case -6:
						break;
					case 6:
						{return new Symbol(sym.por,yyline,yychar, yytext());}
					case -7:
						break;
					case 7:
						{yybegin(ESTADO_CADENA); aux = ""; }
					case -8:
						break;
					case 8:
						{return new Symbol(sym.mas,yyline,yychar, yytext());}
					case -9:
						break;
					case 9:
						{return new Symbol(sym.menos,yyline,yychar, yytext());}
					case -10:
						break;
					case 10:
						{return new Symbol(sym.modulo,yyline,yychar, yytext());}
					case -11:
						break;
					case 11:
						{return new Symbol(sym.parizquierdo,yyline,yychar, yytext());}
					case -12:
						break;
					case 12:
						{return new Symbol(sym.parderecho,yyline,yychar, yytext());}
					case -13:
						break;
					case 13:
						{return new Symbol(sym.igual,yyline,yychar, yytext());}
					case -14:
						break;
					case 14:
						{return new Symbol(sym.menorque,yyline,yychar, yytext());}
					case -15:
						break;
					case 15:
						{return new Symbol(sym.mayorque,yyline,yychar, yytext());}
					case -16:
						break;
					case 16:
						{return new Symbol(sym.not,yyline,yychar, yytext());}
					case -17:
						break;
					case 17:
						{return new Symbol(sym.xor,yyline,yychar, yytext());}
					case -18:
						break;
					case 18:
						{return new Symbol(sym.coma,yyline,yychar, yytext());}
					case -19:
						break;
					case 19:
						{return new Symbol(sym.puntoycoma,yyline,yychar, yytext());}
					case -20:
						break;
					case 20:
						{return new Symbol(sym.llaveizq,yyline,yychar, yytext());}
					case -21:
						break;
					case 21:
						{return new Symbol(sym.llaveder,yyline,yychar, yytext());}
					case -22:
						break;
					case 22:
						{return new Symbol(sym.dospuntos,yyline,yychar, yytext());}
					case -23:
						break;
					case 23:
						{return new Symbol(sym.interrogacion,yyline,yychar, yytext());}
					case -24:
						break;
					case 24:
						{return new Symbol(sym.corizquierdo,yyline,yychar, yytext());}
					case -25:
						break;
					case 25:
						{return new Symbol(sym.corderecho,yyline,yychar, yytext());}
					case -26:
						break;
					case 26:
						{return new Symbol(sym.punto,yyline,yychar, yytext());}
					case -27:
						break;
					case 27:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -28:
						break;
					case 28:
						{return new Symbol(sym.entero,yyline,yychar, yytext());}
					case -29:
						break;
					case 29:
						{yybegin(COMENTARIO);}
					case -30:
						break;
					case 30:
						{yybegin(COMENTARIO_MULTI);}
					case -31:
						break;
					case 31:
						{return new Symbol(sym.potencia,yyline,yychar,yytext());}
					case -32:
						break;
					case 32:
						{return new Symbol(sym.masmas,yyline,yychar, yytext());}
					case -33:
						break;
					case 33:
						{return new Symbol(sym.menosmenos,yyline,yychar, yytext());}
					case -34:
						break;
					case 34:
						{return new Symbol(sym.igualigual,yyline,yychar, yytext());}
					case -35:
						break;
					case 35:
						{return new Symbol(sym.menorigualque,yyline,yychar, yytext());}
					case -36:
						break;
					case 36:
						{return new Symbol(sym.mayorigualque,yyline,yychar, yytext());}
					case -37:
						break;
					case 37:
						{return new Symbol(sym.diferenteque,yyline,yychar, yytext());}
					case -38:
						break;
					case 38:
						{return new Symbol(sym.or,yyline,yychar, yytext());}
					case -39:
						break;
					case 39:
						{return new Symbol(sym.and,yyline,yychar, yytext());}
					case -40:
						break;
					case 40:
						{return new Symbol(sym.rif,yyline,yychar, yytext());}
					case -41:
						break;
					case 41:
						{return new Symbol(sym.rdo,yyline,yychar, yytext());}
					case -42:
						break;
					case 42:
						{return new Symbol(sym.rint,yyline,yychar, yytext());}
					case -43:
						break;
					case 43:
						{return new Symbol(sym.rnew,yyline,yychar, yytext());}
					case -44:
						break;
					case 44:
						{return new Symbol(sym.rstr,yyline,yychar, yytext());}
					case -45:
						break;
					case 45:
						{return new Symbol(sym.rpow,yyline,yychar, yytext());}
					case -46:
						break;
					case 46:
						{return new Symbol(sym.rfor,yyline,yychar, yytext());}
					case -47:
						break;
					case 47:
						{return new Symbol(sym.doble,yyline,yychar, yytext());}
					case -48:
						break;
					case 48:
						{ return new Symbol(sym.caracter,yyline,yychar, yytext());}
					case -49:
						break;
					case 49:
						{return new Symbol(sym.rnull,yyline,yychar, yytext());}
					case -50:
						break;
					case 50:
						{return new Symbol(sym.rthis,yyline,yychar, yytext());}
					case -51:
						break;
					case 51:
						{return new Symbol(sym.rtrue,yyline,yychar, yytext());}
					case -52:
						break;
					case 52:
						{return new Symbol(sym.relse,yyline,yychar, yytext());}
					case -53:
						break;
					case 53:
						{return new Symbol(sym.rchar,yyline,yychar, yytext());}
					case -54:
						break;
					case 54:
						{return new Symbol(sym.rcase,yyline,yychar, yytext());}
					case -55:
						break;
					case 55:
						{return new Symbol(sym.rread,yyline,yychar, yytext());}
					case -56:
						break;
					case 56:
						{return new Symbol(sym.rvoid,yyline,yychar, yytext());}
					case -57:
						break;
					case 57:
						{return new Symbol(sym.rtoInt,yyline,yychar, yytext());}
					case -58:
						break;
					case 58:
						{return new Symbol(sym.rbreak,yyline,yychar, yytext());}
					case -59:
						break;
					case 59:
						{return new Symbol(sym.rclass,yyline,yychar, yytext());}
					case -60:
						break;
					case 60:
						{return new Symbol(sym.rgraph,yyline,yychar, yytext());}
					case -61:
						break;
					case 61:
						{return new Symbol(sym.rsuper,yyline,yychar, yytext());}
					case -62:
						break;
					case 62:
						{return new Symbol(sym.rprint,yyline,yychar, yytext());}
					case -63:
						break;
					case 63:
						{return new Symbol(sym.rfinal,yyline,yychar, yytext());}
					case -64:
						break;
					case 64:
						{return new Symbol(sym.rfalse,yyline,yychar, yytext());}
					case -65:
						break;
					case 65:
						{return new Symbol(sym.rwhile,yyline,yychar, yytext());}
					case -66:
						break;
					case 66:
						{return new Symbol(sym.rimport,yyline,yychar, yytext());}
					case -67:
						break;
					case 67:
						{return new Symbol(sym.rtoChar,yyline,yychar, yytext());}
					case -68:
						break;
					case 68:
						{return new Symbol(sym.rdouble,yyline,yychar, yytext());}
					case -69:
						break;
					case 69:
						{return new Symbol(sym.rreturn,yyline,yychar, yytext());}
					case -70:
						break;
					case 70:
						{return new Symbol(sym.rstring,yyline,yychar, yytext());}
					case -71:
						break;
					case 71:
						{return new Symbol(sym.rstatic,yyline,yychar, yytext());}
					case -72:
						break;
					case 72:
						{return new Symbol(sym.rswitch,yyline,yychar, yytext());}
					case -73:
						break;
					case 73:
						{return new Symbol(sym.rpublic,yyline,yychar, yytext());}
					case -74:
						break;
					case 74:
						{return new Symbol(sym.rdefault,yyline,yychar, yytext());}
					case -75:
						break;
					case 75:
						{return new Symbol(sym.rboolean,yyline,yychar, yytext());}
					case -76:
						break;
					case 76:
						{return new Symbol(sym.rextends,yyline,yychar, yytext());}
					case -77:
						break;
					case 77:
						{return new Symbol(sym.rprintln,yyline,yychar, yytext());}
					case -78:
						break;
					case 78:
						{return new Symbol(sym.rprivate,yyline,yychar, yytext());}
					case -79:
						break;
					case 79:
						{return new Symbol(sym.rtoDouble,yyline,yychar, yytext());}
					case -80:
						break;
					case 80:
						{return new Symbol(sym.rcontinue,yyline,yychar, yytext());}
					case -81:
						break;
					case 81:
						{return new Symbol(sym.rabstract,yyline,yychar, yytext());}
					case -82:
						break;
					case 82:
						{return new Symbol(sym.rread_file,yyline,yychar, yytext());}
					case -83:
						break;
					case 83:
						{return new Symbol(sym.rprotected,yyline,yychar, yytext());}
					case -84:
						break;
					case 84:
						{return new Symbol(sym.rinstanceof,yyline,yychar, yytext());}
					case -85:
						break;
					case 85:
						{return new Symbol(sym.rprinttabla,yyline,yychar, yytext());}
					case -86:
						break;
					case 86:
						{return new Symbol(sym.rwrite_file,yyline,yychar, yytext());}
					case -87:
						break;
					case 87:
						{return new Symbol(sym.rgraficardot,yyline,yychar,yytext());}
					case -88:
						break;
					case 88:
						{return new Symbol(sym.rgraficarentorno,yyline,yychar,yytext());}
					case -89:
						break;
					case 89:
						{yybegin(YYINITIAL);}
					case -90:
						break;
					case 90:
						{}
					case -91:
						break;
					case 91:
						{}
					case -92:
						break;
					case 92:
						{ yychar=1;}
					case -93:
						break;
					case 93:
						{}
					case -94:
						break;
					case 94:
						{}
					case -95:
						break;
					case 95:
						{yybegin(YYINITIAL);}
					case -96:
						break;
					case 96:
						{aux = aux + yytext(); }
					case -97:
						break;
					case 97:
						{yybegin(YYINITIAL); return new Symbol(sym.cadena,yyline,yychar,aux);}
					case -98:
						break;
					case 99:
						{
    cError error=new cError("Lexico",yytext(),yyline,yychar);
    ProyectCompi1.errores.add(error);
}
					case -99:
						break;
					case 100:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -100:
						break;
					case 101:
						{}
					case -101:
						break;
					case 103:
						{
    cError error=new cError("Lexico",yytext(),yyline,yychar);
    ProyectCompi1.errores.add(error);
}
					case -102:
						break;
					case 104:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -103:
						break;
					case 106:
						{
    cError error=new cError("Lexico",yytext(),yyline,yychar);
    ProyectCompi1.errores.add(error);
}
					case -104:
						break;
					case 107:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -105:
						break;
					case 109:
						{
    cError error=new cError("Lexico",yytext(),yyline,yychar);
    ProyectCompi1.errores.add(error);
}
					case -106:
						break;
					case 110:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -107:
						break;
					case 112:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -108:
						break;
					case 114:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -109:
						break;
					case 115:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -110:
						break;
					case 116:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -111:
						break;
					case 117:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -112:
						break;
					case 118:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -113:
						break;
					case 119:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -114:
						break;
					case 120:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -115:
						break;
					case 121:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -116:
						break;
					case 122:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -117:
						break;
					case 123:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -118:
						break;
					case 124:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -119:
						break;
					case 125:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -120:
						break;
					case 126:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -121:
						break;
					case 127:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -122:
						break;
					case 128:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -123:
						break;
					case 129:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -124:
						break;
					case 130:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -125:
						break;
					case 131:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -126:
						break;
					case 132:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -127:
						break;
					case 133:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -128:
						break;
					case 134:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -129:
						break;
					case 135:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -130:
						break;
					case 136:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -131:
						break;
					case 137:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -132:
						break;
					case 138:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -133:
						break;
					case 139:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -134:
						break;
					case 140:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -135:
						break;
					case 141:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -136:
						break;
					case 142:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -137:
						break;
					case 143:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -138:
						break;
					case 144:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -139:
						break;
					case 145:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -140:
						break;
					case 146:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -141:
						break;
					case 147:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -142:
						break;
					case 148:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -143:
						break;
					case 149:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -144:
						break;
					case 150:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -145:
						break;
					case 151:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -146:
						break;
					case 152:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -147:
						break;
					case 153:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -148:
						break;
					case 154:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -149:
						break;
					case 155:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -150:
						break;
					case 156:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -151:
						break;
					case 157:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -152:
						break;
					case 158:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -153:
						break;
					case 159:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -154:
						break;
					case 160:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -155:
						break;
					case 161:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -156:
						break;
					case 162:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -157:
						break;
					case 163:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -158:
						break;
					case 164:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -159:
						break;
					case 165:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -160:
						break;
					case 166:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -161:
						break;
					case 167:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -162:
						break;
					case 168:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -163:
						break;
					case 169:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -164:
						break;
					case 170:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -165:
						break;
					case 171:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -166:
						break;
					case 172:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -167:
						break;
					case 173:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -168:
						break;
					case 174:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -169:
						break;
					case 175:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -170:
						break;
					case 176:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -171:
						break;
					case 177:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -172:
						break;
					case 178:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -173:
						break;
					case 179:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -174:
						break;
					case 180:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -175:
						break;
					case 181:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -176:
						break;
					case 182:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -177:
						break;
					case 183:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -178:
						break;
					case 184:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -179:
						break;
					case 185:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -180:
						break;
					case 186:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -181:
						break;
					case 187:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -182:
						break;
					case 188:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -183:
						break;
					case 189:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -184:
						break;
					case 190:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -185:
						break;
					case 191:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -186:
						break;
					case 192:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -187:
						break;
					case 193:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -188:
						break;
					case 194:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -189:
						break;
					case 195:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -190:
						break;
					case 196:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -191:
						break;
					case 197:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -192:
						break;
					case 198:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -193:
						break;
					case 199:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -194:
						break;
					case 200:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -195:
						break;
					case 201:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -196:
						break;
					case 202:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -197:
						break;
					case 203:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -198:
						break;
					case 204:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -199:
						break;
					case 205:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -200:
						break;
					case 206:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -201:
						break;
					case 207:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -202:
						break;
					case 208:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -203:
						break;
					case 209:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -204:
						break;
					case 210:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -205:
						break;
					case 211:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -206:
						break;
					case 212:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -207:
						break;
					case 213:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -208:
						break;
					case 214:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -209:
						break;
					case 215:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -210:
						break;
					case 216:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -211:
						break;
					case 217:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -212:
						break;
					case 218:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -213:
						break;
					case 219:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -214:
						break;
					case 220:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -215:
						break;
					case 221:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -216:
						break;
					case 222:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -217:
						break;
					case 223:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -218:
						break;
					case 224:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -219:
						break;
					case 225:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -220:
						break;
					case 226:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -221:
						break;
					case 227:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -222:
						break;
					case 228:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -223:
						break;
					case 229:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -224:
						break;
					case 230:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -225:
						break;
					case 231:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -226:
						break;
					case 232:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -227:
						break;
					case 233:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -228:
						break;
					case 234:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -229:
						break;
					case 235:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -230:
						break;
					case 236:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -231:
						break;
					case 237:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -232:
						break;
					case 238:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -233:
						break;
					case 239:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -234:
						break;
					case 240:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -235:
						break;
					case 241:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -236:
						break;
					case 242:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -237:
						break;
					case 243:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -238:
						break;
					case 244:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -239:
						break;
					case 245:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -240:
						break;
					case 246:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -241:
						break;
					case 247:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -242:
						break;
					case 248:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -243:
						break;
					case 249:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -244:
						break;
					case 250:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -245:
						break;
					case 251:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -246:
						break;
					case 252:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -247:
						break;
					case 253:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -248:
						break;
					case 254:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -249:
						break;
					case 255:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -250:
						break;
					case 256:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -251:
						break;
					case 257:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -252:
						break;
					case 258:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -253:
						break;
					case 259:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -254:
						break;
					case 260:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -255:
						break;
					case 261:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -256:
						break;
					case 262:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -257:
						break;
					case 263:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -258:
						break;
					case 264:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -259:
						break;
					case 265:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -260:
						break;
					case 266:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -261:
						break;
					case 267:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -262:
						break;
					case 268:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -263:
						break;
					case 269:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -264:
						break;
					case 270:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -265:
						break;
					case 271:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -266:
						break;
					case 272:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -267:
						break;
					case 273:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -268:
						break;
					case 274:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -269:
						break;
					case 275:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -270:
						break;
					case 276:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -271:
						break;
					case 277:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -272:
						break;
					case 278:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -273:
						break;
					case 279:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -274:
						break;
					case 280:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -275:
						break;
					case 281:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -276:
						break;
					case 282:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -277:
						break;
					case 283:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -278:
						break;
					case 284:
						{return new Symbol(sym.id,yyline,yychar, yytext());}
					case -279:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
