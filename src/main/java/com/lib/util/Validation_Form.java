package com.lib.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Validation_Form {

	private static final Logger logger = LoggerFactory.getLogger(Validation_Form.class);
	 

	public static boolean removeTag(String input)
	{
		String converStr = input;
		converStr=converStr.replaceAll("^*<*", "");
		converStr=converStr.replaceAll("^*>*", "");
		
		int inputLength= input.length();
		int converStrLength= converStr.length();
		if(inputLength != converStrLength)
		{
			return false;
		}
		return true;
	}
	
	public static boolean validAllEmpty(String input)
	{
		String str =input.replaceAll("\\p{Z}", "");
		int length = str.length();
		int length2 = input.length();
		
		if(length!=length2)
		{
			return false;
		}
		return true;
	}
	
	public static boolean validLength(int min,int max,String input)
	{
		if((input.length()<min) || (input.length() > max))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static boolean validKorean(String input)
	{      
		/* ^[가-힣]*$ - 정 규 표 현 식 , 한 글 인 지 체 크 . */
		Pattern p = Pattern.compile("^[가-힣]*$");
		Matcher m = p.matcher(input);
		
		if(m.find())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean validIncludeEng(String input)
	{
		Pattern p = Pattern.compile("^(?=.*?[a-zA-Z]).*$");
		Matcher m = p.matcher(input);
		if(m.find())
		{
			return true;
		}
		return false;
	}

	public static boolean validIncludeNum(String input)
	{
		Pattern p = Pattern.compile("^(?=.*?[0-9]).*$");
		Matcher m = p.matcher(input);
		if(m.find())
		{
			return true;
		}
		return false;
	}

	public static boolean validNoSpecial(String input)
	{
		Pattern p = Pattern.compile("^[a-zA-Z0-9가-힣]*$");
		Matcher m = p.matcher(input);
		
		if(m.find())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static boolean validChinaChar(String input)
	{
		Pattern	p = Pattern.compile("^[가-힣a-zA-Z0-9`~!@#$%^&*()-=_+\\[\\]{}:;',./<>?\\\\|]*$");
		Matcher m = p.matcher(input);
		m = p.matcher(input);
		if(m.find())
		{
			return false;
		}
		return true;
	}
/* 정 규 표 현 식 을 사 용 해 서 숫 자 만 사 용 되 었 는 지  */
	public static boolean validNum(String input)
	{
		if(input.equals(""))
		{
			return false;
		}
		
		Pattern p = Pattern.compile("^[0-9]*$");
		Matcher m = p.matcher(input);
		
		if(m.find())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean validEngNum(String input)
	{
		Pattern p = Pattern.compile("^[a-zA-Z0-9]*$");
		Matcher m = p.matcher(input);
		
		if(m.find())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean validContinueChar(String input)
	{
		Pattern p = Pattern.compile("(\\w)\\1\\1\\1\\1");
		Matcher m = p.matcher(input);
		if(m.find())
		{
			return false;
		}
		return true;
	}
	
	public static boolean validCombineChar(String input)
	{
		boolean a1 = validNoSpecial(input);
		boolean a2 = validIncludeNum(input);
		boolean a3 = validIncludeEng(input);
		if(a1==false&&a2==true&&a3==true)
		{
			return true;
		}
		return false;
	}

	public static boolean validPassword(String input,String input2,int min,int max)
	{
		String str =input.replaceAll("\\p{Z}", "");
		String str2 =input2.replaceAll("\\p{Z}", "");
		int length = str.length();
		int length2 = input.length();
		
		if(str.length() <= 0)
		{
			return false;
		}
		
		
		if((str.length()<min) || (str.length() > max))
		{
			return false;
		}
		
		if(length!=length2)
		{
			return false;
		}
		
		Pattern p = Pattern.compile("^*&");
		Matcher m = p.matcher(input);
		if(m.find())
		{
			return false;
		}
		
		if(validChinaChar(input))
		{
			return false;
		}

		p = Pattern.compile("(\\w)\\1\\1\\1\\1");
		m = p.matcher(input);
		if(m.find())
		{
			return false;
		}
		if(!validCombineChar(input))
		{
			return false;
		}
		if((str.length() > 0)&&(str2.length() <= 0))
		{
			return false;
		}
		
		if(!str.equals(str2))
		{
			return false;
		}
		
		return true;
	}

	public static boolean isEmpty(String input)
	{
		int length = input.replaceAll("\\p{Z}", "").length();
		int length2 = input.length();
		
		if(length==length2)
		{
			return true;
		}
		else
		{
			logger.error("Form_validLength 길이틀림");
			return false;
		}
	}
	
	public static boolean validPhone(String input)
	{
		Pattern p = Pattern.compile("^\\d{3}\\d{3,4}\\d{4}$");
		Matcher m = p.matcher(input);
		if(m.find())
		{
			return true;
		}
		else
		{
			logger.error("유효하지 않은 전화번호:"+input);
			return false;
		}
	}
	
	public static boolean validEmail(String input)
	{
		Pattern p = Pattern.compile("^[-!#$%&'*+./0-9=?A-Z^_a-z{|}~]+@[-!#$%&'*+/0-9=?A-Z^_a-z{|}~]+.[-!#$%&'*+./0-9=?A-Z^_a-z{|}~]+$");
		Matcher m = p.matcher(input);
		if(m.find())
		{
			return true;
		}	
		else
		{
			logger.error("유효하지 않은 이메일:"+input);
			return false;	
		}
	}
	
	//주민등록번호 유효성체크
	public static boolean validPersonNumber(String input) throws Exception{
        if (input.length() != 13)
        {
        	return false;
        }
 
        String leftSid = input.substring(0, 6);
        String rightSid = input.substring(6, 13);
 
        int yy = Integer.parseInt(leftSid.substring(0, 2));
        int mm = Integer.parseInt(leftSid.substring(2, 4));
        int dd = Integer.parseInt(leftSid.substring(4, 6));
        
        if (yy > 99 || mm > 12 || mm < 1 || dd < 1 || dd > 31) return false;
 
        int digit1 = Integer.parseInt(leftSid.substring(0, 1)) * 2;
        int digit2 = Integer.parseInt(leftSid.substring(1, 2)) * 3;
        int digit3 = Integer.parseInt(leftSid.substring(2, 3)) * 4;
        int digit4 = Integer.parseInt(leftSid.substring(3, 4)) * 5;
        int digit5 = Integer.parseInt(leftSid.substring(4, 5)) * 6;
        int digit6 = Integer.parseInt(leftSid.substring(5, 6)) * 7;
 
        int digit7 = Integer.parseInt(rightSid.substring(0, 1)) * 8;
        int digit8 = Integer.parseInt(rightSid.substring(1, 2)) * 9;
        int digit9 = Integer.parseInt(rightSid.substring(2, 3)) * 2;
        int digit10 = Integer.parseInt(rightSid.substring(3, 4)) * 3;
        int digit11 = Integer.parseInt(rightSid.substring(4, 5)) * 4;
        int digit12 = Integer.parseInt(rightSid.substring(5, 6)) * 5;
 
        int last_digit = Integer.parseInt(rightSid.substring(6, 7));
 
        int error_verify = (digit1 + digit2 + digit3 + digit4 + digit5 + digit6 + digit7 + digit8 + digit9 + digit10 + digit11 + digit12) % 11;
 
        int sum_digit = 0;
        if (error_verify == 0) {
            sum_digit = 1;
        } else if (error_verify == 1) {
            sum_digit = 0;
        } else {
            sum_digit = 11 - error_verify;
        }
 
        if (last_digit == sum_digit)
        {
        	return true;
        }
        else
        {
        	return false;
        }
		
	}
}
