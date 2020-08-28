import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompleteDem {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletableFuture<String> cf = CompletableFuture.supplyAsync(()->{
			System.out.println("Hello");
			try {
				Thread.sleep(2000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return "Hello";
		}).thenCompose(value-> {
			System.out.println(value);
		    String str = value.toUpperCase();
		    return CompletableFuture.completedFuture(str);
		});
		System.out.println("Value- " + cf.get());
	}

}
