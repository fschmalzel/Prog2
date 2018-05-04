package console.better.ui.sample;

import java.lang.reflect.Method;

import console.better.ui.AsCommand;

public class UserActionsImpl implements UserActions {

	@Override
	public void help() {
		
		for (Method m : this.getClass().getInterfaces()[0].getDeclaredMethods()) {
			AsCommand ac = m.getAnnotation(AsCommand.class);
			if (ac != null)
				System.out.println(ac.getName() + "\t" + ac.getHelpText());
		}
	}

	@Override
	public void exit() {
		System.exit(0);
	}

	@Override
	public void addi(Integer i, Integer j) {
		System.out.println(i + j);
	}

	@Override
	public void addf(Float i, Float j) {
		System.out.println(i + j);

	}

	@Override
	public void echo(String s, Integer count) {
		for (int i = 0; i < count; i++)
			System.out.println(s);
	}

}
