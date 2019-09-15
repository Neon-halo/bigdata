package day0910.salarytotal;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class SalaryTotalReducer extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {

	@Override
	protected void reduce(IntWritable k3, Iterable<IntWritable> v3,Context context)
			throws IOException, InterruptedException {
//对v3求和
		int total = 0;
		for (IntWritable v : v3) {
			total = total + v.get();
		}
		
//		输出 k4 部门号	v4是部门的工资总数
		context.write(k3, new IntWritable(total));
	}

}
