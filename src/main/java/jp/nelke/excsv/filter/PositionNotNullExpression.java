/**
 * 
 */
package jp.nelke.excsv.filter;

/**
 * @author USER-1
 */
public class PositionNotNullExpression extends PositionExpression {

    protected PositionNotNullExpression(int position) {
        super(position);
    }

    @Override
    protected boolean isAccept(Object value) {
        return value != null;
    }

}
