package com.theOnlyHorst.ChessDiscordBot.ChessPlayer;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
public class Board {
    public static final int A1 = 0;
    public static final int B1 = 1;
    public static final int C1 = 2;
    public static final int D1 = 3;
    public static final int E1 = 4;
    public static final int F1 = 5;
    public static final int G1 = 6;
    public static final int H1 = 7;
    public static final int A2 = 8;
    public static final int B2 = 9;
    public static final int C2 = 10;
    public static final int D2 = 11;
    public static final int E2 = 12;
    public static final int F2 = 13;
    public static final int G2 = 14;
    public static final int H2 = 15;
    public static final int A3 = 16;
    public static final int B3 = 17;
    public static final int C3 = 18;
    public static final int D3 = 19;
    public static final int E3 = 20;
    public static final int F3 = 21;
    public static final int G3 = 22;
    public static final int H3 = 23;
    public static final int A4 = 24;
    public static final int B4 = 25;
    public static final int C4 = 26;
    public static final int D4 = 27;
    public static final int E4 = 28;
    public static final int F4 = 29;
    public static final int G4 = 30;
    public static final int H4 = 31;
    public static final int A5 = 32;
    public static final int B5 = 33;
    public static final int C5 = 34;
    public static final int D5 = 35;
    public static final int E5 = 36;
    public static final int F5 = 37;
    public static final int G5 = 38;
    public static final int H5 = 39;
    public static final int A6 = 40;
    public static final int B6 = 41;
    public static final int C6 = 42;
    public static final int D6 = 43;
    public static final int E6 = 44;
    public static final int F6 = 45;
    public static final int G6 = 46;
    public static final int H6 = 47;
    public static final int A7 = 48;
    public static final int B7 = 49;
    public static final int C7 = 50;
    public static final int D7 = 51;
    public static final int E7 = 52;
    public static final int F7 = 53;
    public static final int G7 = 54;
    public static final int H7 = 55;
    public static final int A8 = 56;
    public static final int B8 = 57;
    public static final int C8 = 58;
    public static final int D8 = 59;
    public static final int E8 = 60;
    public static final int F8 = 61;
    public static final int G8 = 62;
    public static final int H8 = 63;

    public static final int EMPTY = 0;

    public static final int PAWNW = 1;
    public static final int ROOKW = 2;
    public static final int KNIGHTW = 3;
    public static final int BISHOPW = 4;
    public static final int QUEENW= 5;
    public static final int KINGW = 6;

    public static final int PAWNB = 7;
    public static final int ROOKB = 8;
    public static final int KNIGHTB = 9;
    public static final int BISHOPB = 10;
    public static final int QUEENB = 11;
    public static final int KINGB = 12;


    private static final Map<Character,Integer> FENCharToPieceValue;

    private static final Map<String,Integer> FieldStringToInteger;

    static {
        Map<Character, Integer> aMap = new HashMap<>();

        aMap.put('P',1);
        aMap.put('R',2);
        aMap.put('N',3);
        aMap.put('B',4);
        aMap.put('Q',5);
        aMap.put('K',6);
        aMap.put('p',7);
        aMap.put('r',8);
        aMap.put('n',9);
        aMap.put('b',10);
        aMap.put('q',11);
        aMap.put('k',12);

        FENCharToPieceValue = Collections.unmodifiableMap(aMap);

        Map<String,Integer> bMap = new HashMap<>();

        for(int i=0;i<64;i++)
        {
            int rownum = i/8 +1;
            int colnum = i%8 +1;

            char colChar = (char)(colnum+97);

            bMap.put(String.valueOf(colChar)+rownum,i);

        }


        FieldStringToInteger = Collections.unmodifiableMap(bMap);

    }

    private static final Pattern FENPATTERN = Pattern.compile("^((?:[rnbqkpRNBQKP1-8]{1,8}/){7}[rnbqkpRNBQKP1-8]{1,8}) ([wb]) (K?Q?k?q?|-) ([a-h][1-8]|-) (\\d+) (\\d+)$");

    private HashMap<Integer,Integer> boardMap;

    //-1 means no field to castle
    private int enPassantField;

    private boolean blackMove;

    private boolean castleKingW;
    private boolean castleQueenW;
    private boolean castleKingB;
    private boolean castleQueenB;

    private int halfmoves;
    private int movecount;

    private Board()
    {

    }

    public static Board ofFEN(String FEN)
    {
        Matcher m = FENPATTERN.matcher(FEN);
        if(m.matches())
        {
            Board b = new Board();
            String position = m.group(1);
            int rownum = 7;
            for(String row:position.split("/"))
            {
                int columnNum = 0;
                  for(char c:row.toCharArray())
                  {
                      if(Character.isDigit(c)) {
                          int x = Integer.parseInt(String.valueOf(c));
                          for (int i = 0; i < x; i++) {
                              b.getBoardMap().put(rownum *8 + columnNum, 0);
                              columnNum++;
                          }
                      }
                      else
                      {
                          b.getBoardMap().put(rownum* columnNum,FENCharToPieceValue.get(c));
                          columnNum++;
                      }
                  }
                  rownum--;
            }
            String toMove = m.group(2);
            if(toMove.equals("b"))
            {
                b.setBlackMove(true);
            }

            String castles = m.group(3);
            if(castles.contains("K"))
                b.setCastleKingW(true);
            if(castles.contains("Q"))
                b.setCastleQueenW(true);
            if(castles.contains("k"))
                b.setCastleKingB(true);
            if(castles.contains("q"))
                b.setCastleQueenB(true);

            String enPassantField = m.group(4);
            if("-".equals(enPassantField))
            {
                b.setEnPassantField(-1);
            }
            else
            {
                b.setEnPassantField(FieldStringToInteger.get(enPassantField));
            }

            b.setHalfmoves(Integer.parseInt(m.group(5)));
            b.setMovecount(Integer.parseInt(m.group(6)));

            return b;
        }else
        {
            throw new RuntimeException("Supplied String is not a FEN");
        }



    }

    public String toFEN()
    {
        List<String> rows = new ArrayList<>();
        for(int i=7;i>=0;i--)
        {
            char[] rowPieces = new char[8];
            for(int x = 0;x<8;x++)
            {
                int pieceNum = boardMap.get(i*8+x);
                switch (pieceNum)
                {
                    case 0:
                        rowPieces[x] = 'e';
                        break;
                    case 1:
                        rowPieces[x] = 'P';
                        break;
                    case 2:
                        rowPieces[x] = 'R';
                        break;
                    case 3:
                        rowPieces[x] = 'N';
                        break;
                    case 4:
                        rowPieces[x] = 'B';
                        break;
                    case 5:
                        rowPieces[x] = 'Q';
                        break;
                    case 6:
                        rowPieces[x] = 'K';
                        break;
                    case 7:
                        rowPieces[x] = 'p';
                        break;
                    case 8:
                        rowPieces[x] = 'r';
                        break;
                    case 9:
                        rowPieces[x] = 'n';
                        break;
                    case 10:
                        rowPieces[x] = 'b';
                        break;
                    case 11:
                        rowPieces[x] = 'q';
                        break;
                    case 12:
                        rowPieces[x] = 'k';
                        break;
                }
            }
            String row = String.valueOf(rowPieces);
            String cleanedRow = "";
            int countEmpt =0;
            for(int x =0;x<row.length();x++)
            {
                if(row.charAt(x)=='e')
                {
                    countEmpt++;
                }
                else if(countEmpt!=0)
                {
                    cleanedRow = row.replaceFirst("e+",String.valueOf(countEmpt));
                }
            }
            rows.add(cleanedRow);
        }

        String rowString = String.join("/",rows);

        String toMove = blackMove?"b":"w";

        String castles = (castleKingW?"K":"") + (castleQueenW?"Q":"") + (castleKingB?"k":"") + (castleQueenB?"q":"");

        String enPassant;
        if (enPassantField==-1)
        {
            enPassant = "-";
        }
        else
        {
            int enPassantCol = enPassantField%8;
            char enPassantColChar = (char)(enPassantCol+97);
            enPassant = String.valueOf(enPassantColChar)+ enPassantField/8;
        }

        String halfMov = String.valueOf(halfmoves);
        String fulMov = String.valueOf(movecount);


        return String.join(" ",rowString,toMove,castles,enPassant,halfMov,fulMov);
    }

    public Board makeMove(String move)
    {


        return null;
    }

}
