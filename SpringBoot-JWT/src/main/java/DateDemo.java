import java.util.Date;

public class DateDemo {

	public static void main(String[] args) {
		long s = new Date().getTime();
		System.out.println(s);
		System.out.println(new Date(1587802924*1000L));
		
//		1587802770227
//		1587802586163

	}

}
