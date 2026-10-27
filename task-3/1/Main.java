void main() {
    int x =100+(new java.util.Random()).nextInt(900);
    int max=0;

    if(max<x%10){
        max=x%10;
    }

    if(max<(x/10)%10){
        max=(x/10)%10;
    }

    if(max<(x/100)){
        max=x/100;
    }

    System.out.println("Случайное трехзначное число: "+x);
    System.out.println("Наибольшая цифра в числе: "+max);
}
