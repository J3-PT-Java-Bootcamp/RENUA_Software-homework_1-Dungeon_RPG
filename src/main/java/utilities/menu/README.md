# How to implement the Menu class
## Instantiating MenuOptions
First you need to define the options that will be available on the menu. In order to do that you need to create an array of *MenuOption*s.
This class accepts a generic type that will represent the value of the option (in the example it will be an *Integer*).

If you wanted to create a list to display three fruits with their IDs (apple = 32, Banana = 45 and Orange = 12) you could do it this way:
```java
var options = new MenuOption[] {
    MenuOption.create("Apple",32),
    MenuOption.create("Banana", 45),
    MenuOption.create("Orange", 12)
};
```

There is a third parameter that indicates the availability of the item so, if you wanted to, you could disable some options (for example, the banana) you could do it this way:
```java
var options = new MenuOption[] {
    MenuOption.create("Apple",32),
    MenuOption.create("Banana", 45, false),
    MenuOption.create("Orange", 12)
};
```

## Instantiating a Menu
Once you have defined your options, you can create a *Menu* instance this way:
```java
var menu = new Menu<Integer>(options, "Fruits", "Choose one fruit");
```

And finally, you can display the menu and get the value of the selected option. In this case, the *selected* variable will be an *Integer*. For example, if the user selects an Apple, the value of *selected* will be *32*.
```java
var selected = menu.display();
```