## EventBus
带你一步步实现专属自己的事件总线，使用简洁，事件接收默认即为 UI线程

去除原有EventBus注解功能，效率更好高

## 方式一


 ```
  public class MainActivity extends AppCompatActivity implements Subscriber{

      private Subscriber subscriber;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);

          EventBus.getDefault().register(this);

          Intent intent = new Intent();
          intent.setClass(this, TwoActivity.class);
          startActivity(intent);


      }


      @Override
      protected void onDestroy() {
          super.onDestroy();

          EventBus.getDefault().unregister(this);
      }

      @Override
      public void receive(Object object) {
          Toast.makeText(MainActivity.this, subscriber + "Jam 收到消息了:" + object, Toast.LENGTH_SHORT).show();
      }



  }


 ```


## 方式二

```
 Subscriber subscriber = EventBus.getDefault().register(new Subscriber() {
             @Override
             public void receive(Object object) {
                 Toast.makeText(MainActivity.this, subscriber + "Tom 收到消息了:" + object, Toast.LENGTH_SHORT).show();
             }
         },"tag1");

```



## 消息发送

```
public class TwoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);


        EventBus.getDefault().notifyDataChange("fucking");
        EventBus.getDefault().notifyDataChange("hello","tag1");

    }
}

```



