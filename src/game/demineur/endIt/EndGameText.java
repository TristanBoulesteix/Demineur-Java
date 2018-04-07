package game.demineur.endIt;

import java.awt.Desktop;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

@SuppressWarnings("serial")
public class EndGameText extends JTextPane {
	private ArrayList<String> fiveBestTimes, allScores;
	private int[] currentTime;

	private StringBuilder str = new StringBuilder();

	public EndGameText(ArrayList<String> fiveBestTimes, ArrayList<String> allScores, int[] currentTime,
			boolean victory) {
		this.setEditable(false);
		this.setContentType("text/html");

		this.fiveBestTimes = fiveBestTimes;
		this.allScores = allScores;
		this.currentTime = currentTime;

		if (victory) {
			addVictoryText();
		} else {
			addDefeatText();
		}
	}

	public JScrollPane getScrollBar() {
		JScrollPane scrollPane = new JScrollPane(this);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		return scrollPane;
	}

	private void addVictoryText() {
		str.append("<html>");

		str.append("<p align=\"right\">");
		str.append("<a href=\"http://showAll.prog\">Afficher tous vos temps &rarr</a>");
		str.append("</p>");

		str.append("<center><FONT size=\"5\"><b><u>Victoire !</u></b></FONT></center>");
		str.append("<br />");

		str.append("<p>");
		str.append("Félicitation ! Vous avez gagné ! ");
		str.append("Vous avez trouvé toutes les bombes au bout de ");
		str.append("<i><FONT color=\"blue\">");
		str.append(currentTime[0]);
		str.append(" heure(s), ");
		str.append(currentTime[1]);
		str.append(" minute(s) et ");
		str.append(currentTime[2]);
		str.append(" secondes");
		str.append("</FONT></i>");
		str.append(".");
		str.append("</p>");

		str.append("<p>");
		str.append("Vos cinq meilleurs temps sont :<br />");
		str.append("<ol>");

		for (int i = 0; i < fiveBestTimes.size(); i++) {
			str.append("<li><font color=\"green\">");
			str.append(fiveBestTimes.get(i));
			str.append("</FONT></li>");
		}

		str.append("</ol>");
		str.append("</p>");

		str.append("</p>");
		str.append("Voulez-vous rejouer ?");
		str.append("</p>");

		str.append("</html>");

		this.setText(str.toString());

		this.addlistener(getText());
	}

	private void addlistener(String currentText) {
		this.addHyperlinkListener(new HyperlinkListener() {

			@Override
			public void hyperlinkUpdate(HyperlinkEvent hyp) {
				if (hyp.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					if (Desktop.isDesktopSupported()) {
						if (hyp.getURL().toString().equals("http://showAll.prog")) {
							showAllTimes();
						} else if (hyp.getURL().toString().equals("http://goBack")) {
							setText(currentText);
						}
					}
				}
			}
		});
	}

	private void showAllTimes() {
		StringBuilder builder = new StringBuilder();

		builder.append("<html>");

		builder.append("<p align=\"left\">");
		builder.append("<a href=\"http://goBack\">&larr Retour</a>");
		builder.append("</p>");

		builder.append("Voici tous vos temps :");
		builder.append("<ul");

		for (int i = 0; i < allScores.size(); i++) {
			if (allScores.get(i).equals("Défaite")) {
				builder.append("<li><FONT color=\"red\">");
			} else {
				builder.append("<li><FONT color=\"green\">");
			}
			builder.append(allScores.get(i));
			builder.append("</FONT></li>");
		}

		builder.append("</ul>");

		builder.append("</html>");

		this.setText(builder.toString());
	}

	private void addDefeatText() {
		str.append("<html>");

		str.append("<p align=\"right\">");
		str.append("<a href=\"http://showAll.prog\">Afficher tous vos temps &rarr</a>");
		str.append("</p>");

		str.append("<center><FONT size=\"5\"><b><u>Défaite !</u></b></FONT></center>");
		str.append("<br />");

		str.append("<p>");
		str.append("Vous avez perdu !");
		str.append("Vous avez joué pendant ");
		str.append("<i><FONT color=\"blue\">");
		str.append(currentTime[0]);
		str.append(" heure(s), ");
		str.append(currentTime[1]);
		str.append(" minute(s) et ");
		str.append(currentTime[2]);
		str.append(" secondes");
		str.append("</FONT></i>");
		str.append(".");
		str.append("</p>");

		str.append("</p>");
		str.append("Voulez-vous rejouer ?");
		str.append("</p>");

		str.append("</html>");

		this.setText(str.toString());

		this.addlistener(getText());
	}

	@Override
	public String toString() {
		return this.getText();
	}
}
