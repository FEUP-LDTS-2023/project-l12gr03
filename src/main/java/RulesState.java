public class RulesState extends State<Rule> {

    public RulesState(Rule model) {super(model);}

    @Override
    protected Viewer<Rule> getViewer() {return new RuleViewer(getModel());}

    @Override
    protected Controller<Rule> getController() {return new RuleController(getModel());}
}
