package pay01.school;

public class StudentTest {
	public static void main(String[] args) {
		Student studentArray[] = new Student[3];
		studentArray[0] = new Student("홍길동", 15, 171, 81);
		studentArray[1] = new Student("한사람", 13, 183, 72);
		studentArray[2] = new Student("임걱정", 16, 175, 65);
		
		System.out.println("이름\t나이\t신장\t몸무게" );
		for (int i = 0; i < studentArray.length; i++) {
			studentInfo(studentArray, i);
		}
		
		System.out.println();
		
		System.out.println("나이 평균: " + Math.round((double)(studentArray[0].getAge() + studentArray[1].getAge() 
				+ studentArray[2].getAge()) / studentArray.length * 1000.0) / 1000.0);
		System.out.println("신장 평균: " + Math.round((double)(studentArray[0].getHeight() + studentArray[1].getHeight() 
				+ studentArray[2].getHeight()) / studentArray.length * 1000.0) / 1000.0);
		System.out.println("몸무게 평균: " + Math.round((double)(studentArray[0].getWeight() + studentArray[1].getWeight() 
				+ studentArray[2].getWeight()) / studentArray.length * 1000.0) / 1000.0);
		
		System.out.println();
		
		Student temp1 = null;
		Student temp2 = null;
		
		temp1 = (studentArray[0].getAge() > studentArray[1].getAge()) ? studentArray[0] : studentArray[1];
		temp2 = (temp1.getAge() > studentArray[2].getAge()) ? temp1 : studentArray[2];
		System.out.println("나이가 가장 많은 학생: " + temp2.getName());
		
		temp1 = (studentArray[0].getAge() < studentArray[1].getAge()) ? studentArray[0] : studentArray[1];
		temp2 = (temp1.getAge() < studentArray[2].getAge()) ? temp1 : studentArray[2];
		System.out.println("나이가 가장 적은 학생: " + temp2.getName());
		
		temp1 = (studentArray[0].getHeight() > studentArray[1].getHeight()) ? studentArray[0] : studentArray[1];
		temp2 = (temp1.getHeight() > studentArray[2].getHeight()) ? temp1 : studentArray[2];
		System.out.println("신장이 가장 큰 학생: " + temp2.getName());
		
		temp1 = (studentArray[0].getHeight() < studentArray[1].getHeight()) ? studentArray[0] : studentArray[1];
		temp2 = (temp1.getHeight() < studentArray[2].getHeight()) ? temp1 : studentArray[2];
		System.out.println("신장이 가장 작은 학생: " + temp2.getName());
		
		temp1 = (studentArray[0].getWeight() > studentArray[1].getWeight()) ? studentArray[0] : studentArray[1];
		temp2 = (temp1.getWeight() > studentArray[2].getWeight()) ? temp1 : studentArray[2];
		System.out.println("몸무게가 가장 많이 나가는 학생: " + temp2.getName());
		
		temp1 = (studentArray[0].getWeight() < studentArray[1].getWeight()) ? studentArray[0] : studentArray[1];
		temp2 = (temp1.getWeight() < studentArray[2].getWeight()) ? temp1 : studentArray[2];
		System.out.println("몸무게가 가장 적게 나가는 학생: " + temp2.getName());
	}
	
	public static void studentInfo(Student studentArray[], int i) {
		System.out.println(studentArray[i].getName() + "\t" + studentArray[i].getAge() + "\t" 
						+ studentArray[i].getHeight() + "\t" + studentArray[i].getWeight());
	}
}
