package org.chescript.krana.common.model.action;

import org.chescript.krana.common.model.board.BoardContext;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractAction {
	private String shortDescription;
	private String longDescription;
	
	public ActionEffect action(BoardContext bctx) {
		ActionEffect result = ActionEffect.defaultAction();
		preAction(bctx, result);
		internalAction(bctx, result);
		postAction(bctx, result);
		return result;
	}

	protected abstract void postAction(BoardContext bctx, ActionEffect result);

	protected abstract void internalAction(BoardContext bctx, ActionEffect result);

	protected abstract void preAction(BoardContext bctx, ActionEffect result);
}
