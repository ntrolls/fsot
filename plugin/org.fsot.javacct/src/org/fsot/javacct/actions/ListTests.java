package org.fsot.javacct.actions;

import java.io.File;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.fsot.javacct.common.Helper;
import org.fsot.javacct.common.Views;

public class ListTests implements IObjectActionDelegate {

	private Shell shell;
	private ISelection currentSelection;

	public ListTests() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {

		Object[] listOfTargets;

		try {

			File buildFile = Helper.getBuildFile(currentSelection);
			listOfTargets = Helper.extractListOfTargets(buildFile);
			listOfTargets = Helper.cleanArray(listOfTargets);

		} catch (Exception e) {
			// TODO: ERROR HANDLING
			e.printStackTrace();
			return;
		}

		Object[] compileTarget = Views.getCompileTarget(shell,
				listOfTargets);
		Object[] testTarget = Views.getTestTarget(shell, listOfTargets);

		if (compileTarget == null || testTarget == null) {
			return;
		}
		
		
		
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.currentSelection = selection;
	}
}