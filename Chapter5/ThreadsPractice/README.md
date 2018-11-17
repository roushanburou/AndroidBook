## 用户的渐进式体验

渐进式体验在应用中十分常见，通过渐进式，我们的应用更能提升用户体验，给人一种渐入佳境的感觉，这是平面交互所达不到的效果。

下面我们就举一个简单的例子，代码如下：

```java
public class MainActivity extends AppCompatActivity {

    private Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn4 = (Button) findViewById(R.id.btn4);

        btn4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) { btn4.setBackground(getDrawable(R.drawable.up_button_y_selector));
                btn4.postDelayed(new Runnable() {
                    @Override
                    public void run() {
btn4.setBackground(getDrawable(R.drawable.up_button_r_selector));
                    }
                }, 1000);
                return true;
            }
        });
    }
}
```

## 案例效果演示

这样，我们就能打造出一款具有渐进式的UI了。

![SAMPLE](./result.gif)

## 优化

但是，这样做还是大有问题，如果用户点击第二次，图片就是红色的了。或者，如果用户在图片为黄的时候点击，那么图片也是为红色，问题如下：

![SAMPLE](./result2.gif)

为了解决上述两个问题，我们将代码修改如下：

```java
public class MainActivity extends AppCompatActivity {

    private Button btn4;

    private boolean isRedButton = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn4 = (Button) findViewById(R.id.btn4);

        btn4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) { btn4.setBackground(getDrawable(R.drawable.up_button_y_selector));
                isRedButton = true;
                btn4.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (isRedButton)
                            btn4.setBackground(getDrawable(R.drawable.up_button_r_selector));
                    }
                }, 1000);
                return false;
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRedButton = false; btn4.setBackground(getDrawable(R.drawable.up_button_g_selector));
            }
        });
    }
}
```

通过Flag就能标记就能解决问题了。