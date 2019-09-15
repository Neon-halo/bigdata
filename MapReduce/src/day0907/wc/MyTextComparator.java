package day0907.wc;

import org.apache.hadoop.io.Text;
//针对是 Text 的数据类型，定义自己的比较规则
public class MyTextComparator extends Text.Comparator {

	@Override
	public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
		// TODO Auto-generated method stub
		return -super.compare(b1, s1, l1, b2, s2, l2);
	}
	
}
