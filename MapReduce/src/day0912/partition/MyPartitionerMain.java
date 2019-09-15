package day0912.partition;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MyPartitionerMain {

	public static void main(String[] args) throws Exception {
//		1、创建任务、指定任务的入口
			Job job = Job.getInstance(new Configuration());
			job.setJarByClass(MyPartitionerMain.class);
			
//		2、指定任务的 map 和 map 输出的数据类型
			job.setMapperClass(MyPartitionerMapper.class);
			job.setMapOutputKeyClass(IntWritable.class);	//k2 是部门号
			job.setMapOutputValueClass(Emp.class);	//注意：输出的是员工对象
			
			
//		加入分区规则
			job.setPartitionerClass(MyPartitioner.class);
//		指定分区的个数
			job.setNumReduceTasks(3);
			
			
//		3、指定任务的 reducer 和 reducer 输出的类型
			job.setReducerClass(MyPartitionerReducer.class);
			job.setOutputKeyClass(IntWritable.class);
			job.setOutputValueClass(Emp.class);
			
//		4、指定任务输入路径和输出路径
			FileInputFormat.setInputPaths(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			
//		5、执行任务
			job.waitForCompletion(true);		
		}
}
