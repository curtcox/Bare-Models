/*
 * Copyright 2013 Mario Torre.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 2, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; see the file COPYING.  If not see
 * <http://www.gnu.org/licenses/>.
 *
 * Linking this code with other modules is making a combined work
 * based on this code.  Thus, the terms and conditions of the GNU
 * General Public License cover the whole combination.
 *
 * As a special exception, the copyright holders of this code give
 * you permission to link this code with independent modules to
 * produce an executable, regardless of the license terms of these
 * independent modules, and to copy and distribute the resulting
 * executable under terms of your choice, provided that you also
 * meet, for each linked independent module, the terms and conditions
 * of the license of that module.  An independent module is a module
 * which is not derived from or based on this code.  If you modify
 * this code, you may extend this exception to your version of the
 * library, but you are not obligated to do so.  If you do not wish
 * to do so, delete this exception statement from your version.
 */

package net.baremodels.device.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Creates an {@link Icon} from a give FontAwesome unicode identifier.
 *
 * @see http://fortawesome.github.io/Font-Awesome/cheatsheet/
 */
final class FontAwesomeIcon
    implements Icon
{
    private final int size;
    private final Font font;
    private final char iconID;

    enum Glyph {
        glass ('\uf000'),  music ('\uf001'),  search ('\uf002'),  envelope_o ('\uf003'),  heart ('\uf004'),  star ('\uf005'),  star_o ('\uf006'),  user ('\uf007'),
        film ('\uf008'),  th_large ('\uf009'),  th ('\uf00a'),  th_list ('\uf00b'),  check ('\uf00c'),  times ('\uf00d'),  search_plus ('\uf00e'),
        search_minus ('\uf010'),  power_off ('\uf011'),  signal ('\uf012'),  cog ('\uf013'),  trash_o ('\uf014'),  home ('\uf015'),
        file_o ('\uf016'),  clock_o ('\uf017'),  road ('\uf018'),  download ('\uf019'),  arrow_circle_o_down ('\uf01a'),
        arrow_circle_o_up ('\uf01b'),  inbox ('\uf01c'),  play_circle_o ('\uf01d'),  repeat ('\uf01e'),  refresh ('\uf021'),
        list_alt ('\uf022'),  lock ('\uf023'),  flag ('\uf024'),  headphones ('\uf025'),  volume_off ('\uf026'),  volume_down ('\uf027'),
        volume_up ('\uf028'),  qrcode ('\uf029'),  barcode ('\uf02a'),  tag ('\uf02b'),  tags ('\uf02c'),  book ('\uf02d'),
        bookmark ('\uf02e'),  print ('\uf02f'),  camera ('\uf030'),  font ('\uf031'),  bold ('\uf032'),  italic ('\uf033'),
        text_height ('\uf034'),  text_width ('\uf035'),  align_left ('\uf036'),  align_center ('\uf037'),  align_right ('\uf038'),
        align_justify ('\uf039'),  list ('\uf03a'),  outdent ('\uf03b'),  indent ('\uf03c'),  video_camera ('\uf03d'),
        picture_o ('\uf03e'),  pencil ('\uf040'),  map_marker ('\uf041'),  adjust ('\uf042'),  tint ('\uf043'),
        pencil_square_o ('\uf044'),  share_square_o ('\uf045'),  check_square_o ('\uf046'),  arrows ('\uf047'),
        step_backward ('\uf048'),  fast_backward ('\uf049'),  backward ('\uf04a'),  play ('\uf04b'),  pause ('\uf04c'),
        stop ('\uf04d'),  forward ('\uf04e'),  fast_forward ('\uf050'),  step_forward ('\uf051'),  eject ('\uf052'),
        chevron_left ('\uf053'),  chevron_right ('\uf054'),  plus_circle ('\uf055'),  minus_circle ('\uf056'),
        times_circle ('\uf057'),  check_circle ('\uf058'),  question_circle ('\uf059'),  info_circle ('\uf05a'),
        crosshairs ('\uf05b'),  times_circle_o ('\uf05c'),  check_circle_o ('\uf05d'),  ban ('\uf05e'),
        arrow_left ('\uf060'),  arrow_right ('\uf061'),  arrow_up ('\uf062'),  arrow_down ('\uf063'),
        share ('\uf064'),  expand ('\uf065'),  compress ('\uf066'),  plus ('\uf067'),  minus ('\uf068'),
        asterisk ('\uf069'),  exclamation_circle ('\uf06a'),  gift ('\uf06b'),  leaf ('\uf06c'),  fire ('\uf06d'),
        eye ('\uf06e'),  eye_slash ('\uf070'),  exclamation_triangle ('\uf071'),  plane ('\uf072'),  calendar ('\uf073'),
        random ('\uf074'),  comment ('\uf075'),  magnet ('\uf076'),  chevron_up ('\uf077'),  chevron_down ('\uf078'),
        retweet ('\uf079'),  shopping_cart ('\uf07a'),  folder ('\uf07b'),  folder_open ('\uf07c'),  arrows_v ('\uf07d'),
        arrows_h ('\uf07e'),  bar_chart_o ('\uf080'),  twitter_square ('\uf081'),  facebook_square ('\uf082'),
        camera_retro ('\uf083'),  key ('\uf084'),  cogs ('\uf085'),  comments ('\uf086'),  thumbs_o_up ('\uf087'),
        thumbs_o_down ('\uf088'),  star_half ('\uf089'),  heart_o ('\uf08a'),  sign_out ('\uf08b'),
        linkedin_square ('\uf08c'),  thumb_tack ('\uf08d'),  external_link ('\uf08e'),  sign_in ('\uf090'),
        trophy ('\uf091'),  github_square ('\uf092'),  upload ('\uf093'),  lemon_o ('\uf094'),  phone ('\uf095'),
        square_o ('\uf096'),  bookmark_o ('\uf097'),  phone_square ('\uf098'),  twitter ('\uf099'),  facebook ('\uf09a'),
        github ('\uf09b'),  unlock ('\uf09c'),  credit_card ('\uf09d'),  rss ('\uf09e'),  hdd_o ('\uf0a0'),
        bullhorn ('\uf0a1'),  bell ('\uf0f3'),  certificate ('\uf0a3'),  hand_o_right ('\uf0a4'),  hand_o_left ('\uf0a5'),
        hand_o_up ('\uf0a6'),  hand_o_down ('\uf0a7'),  arrow_circle_left ('\uf0a8'),  arrow_circle_right ('\uf0a9'),
        arrow_circle_up ('\uf0aa'),  arrow_circle_down ('\uf0ab'),  globe ('\uf0ac'),  wrench ('\uf0ad'),
        tasks ('\uf0ae'),  filter ('\uf0b0'),  briefcase ('\uf0b1'),  arrows_alt ('\uf0b2'),  users ('\uf0c0'),
        link ('\uf0c1'),  cloud ('\uf0c2'),  flask ('\uf0c3'),  scissors ('\uf0c4'),  files_o ('\uf0c5'),
        paperclip ('\uf0c6'),  floppy_o ('\uf0c7'),  square ('\uf0c8'),  bars ('\uf0c9'),  list_ul ('\uf0ca'),
        list_ol ('\uf0cb'),  strikethrough ('\uf0cc'),  underline ('\uf0cd'),  table ('\uf0ce'),  magic ('\uf0d0'),
        truck ('\uf0d1'),  pinterest ('\uf0d2'),  pinterest_square ('\uf0d3'),  google_plus_square ('\uf0d4'),
        google_plus ('\uf0d5'),  money ('\uf0d6'),  caret_down ('\uf0d7'),  caret_up ('\uf0d8'),  caret_left ('\uf0d9'),
        caret_right ('\uf0da'),  columns ('\uf0db'),  sort ('\uf0dc'),  sort_asc ('\uf0dd'),  sort_desc ('\uf0de'),
        envelope ('\uf0e0'),  linkedin ('\uf0e1'),  undo ('\uf0e2'),  gavel ('\uf0e3'),  tachometer ('\uf0e4'),
        comment_o ('\uf0e5'),  comments_o ('\uf0e6'),  bolt ('\uf0e7'),  sitemap ('\uf0e8'),  umbrella ('\uf0e9'),
        clipboard ('\uf0ea'),  lightbulb_o ('\uf0eb'),  exchange ('\uf0ec'),  cloud_download ('\uf0ed'),
        cloud_upload ('\uf0ee'),  user_md ('\uf0f0'),  stethoscope ('\uf0f1'),  suitcase ('\uf0f2'),  bell_o ('\uf0a2'),
        coffee ('\uf0f4'),  cutlery ('\uf0f5'),  file_text_o ('\uf0f6'),  building_o ('\uf0f7'),  hospital_o ('\uf0f8'),
        ambulance ('\uf0f9'),  medkit ('\uf0fa'),  fighter_jet ('\uf0fb'),  beer ('\uf0fc'),  h_square ('\uf0fd'),
        plus_square ('\uf0fe'),  angle_double_left ('\uf100'),  angle_double_right ('\uf101'),
        angle_double_up ('\uf102'),  angle_double_down ('\uf103'),  angle_left ('\uf104'),  angle_right ('\uf105'),
        angle_up ('\uf106'),  angle_down ('\uf107'),  desktop ('\uf108'),  laptop ('\uf109'),  tablet ('\uf10a'),
        mobile ('\uf10b'),  circle_o ('\uf10c'),  quote_left ('\uf10d'),  quote_right ('\uf10e'),  spinner ('\uf110'),
        circle ('\uf111'),  reply ('\uf112'),  github_alt ('\uf113'),  folder_o ('\uf114'),  folder_open_o ('\uf115'),
        smile_o ('\uf118'),  frown_o ('\uf119'),  meh_o ('\uf11a'),  gamepad ('\uf11b'),  keyboard_o ('\uf11c'),
        flag_o ('\uf11d'),  flag_checkered ('\uf11e'),  terminal ('\uf120'),  code ('\uf121'),  reply_all ('\uf122'),
        mail_reply_all ('\uf122'),  star_half_o ('\uf123'),  location_arrow ('\uf124'),  crop ('\uf125'),
        code_fork ('\uf126'),  chain_broken ('\uf127'),  question ('\uf128'),  info ('\uf129'),  exclamation ('\uf12a'),
        superscript ('\uf12b'),  subscript ('\uf12c'),  eraser ('\uf12d'),  puzzle_piece ('\uf12e'),  microphone ('\uf130'),
        microphone_slash ('\uf131'),  shield ('\uf132'),  calendar_o ('\uf133'),  fire_extinguisher ('\uf134'),
        rocket ('\uf135'),  maxcdn ('\uf136'),  chevron_circle_left ('\uf137'),  chevron_circle_right ('\uf138'),
        chevron_circle_up ('\uf139'),  chevron_circle_down ('\uf13a'),  html5 ('\uf13b'),  css3 ('\uf13c'),
        anchor ('\uf13d'),  unlock_alt ('\uf13e'),  bullseye ('\uf140'),  ellipsis_h ('\uf141'),  ellipsis_v ('\uf142'),
        rss_square ('\uf143'),  play_circle ('\uf144'),  ticket ('\uf145'),  minus_square ('\uf146'),
        minus_square_o ('\uf147'),  level_up ('\uf148'),  level_down ('\uf149'),  check_square ('\uf14a'),
        pencil_square ('\uf14b'),  external_link_square ('\uf14c'),  share_square ('\uf14d'),  compass ('\uf14e'),
        caret_square_o_down ('\uf150'),  caret_square_o_up ('\uf151'),  caret_square_o_right ('\uf152'),  eur ('\uf153'),
        gbp ('\uf154'),  usd ('\uf155'),  inr ('\uf156'),  jpy ('\uf157'),  rub ('\uf158'),  krw ('\uf159'),
        btc ('\uf15a'),  file ('\uf15b'),  file_text ('\uf15c'),  sort_alpha_asc ('\uf15d'),  sort_alpha_desc ('\uf15e'),
        sort_amount_asc ('\uf160'),  sort_amount_desc ('\uf161'),  sort_numeric_asc ('\uf162'),  sort_numeric_desc ('\uf163'),
        thumbs_up ('\uf164'),  thumbs_down ('\uf165'),  youtube_square ('\uf166'),  youtube ('\uf167'),  xing ('\uf168'),
        xing_square ('\uf169'),  youtube_play ('\uf16a'),  dropbox ('\uf16b'),  stack_overflow ('\uf16c'),
        instagram ('\uf16d'),  flickr ('\uf16e'),  adn ('\uf170'),  bitbucket ('\uf171'),  bitbucket_square ('\uf172'),
        tumblr ('\uf173'),  tumblr_square ('\uf174'),  long_arrow_down ('\uf175'),  long_arrow_up ('\uf176'),
        long_arrow_left ('\uf177'),  long_arrow_right ('\uf178'),  apple ('\uf179'),  windows ('\uf17a'),
        android ('\uf17b'),  linux ('\uf17c'),  dribbble ('\uf17d'),  skype ('\uf17e'),  foursquare ('\uf180'),
        trello ('\uf181'),  female ('\uf182'),  male ('\uf183'),  gittip ('\uf184'),  sun_o ('\uf185'),
        moon_o ('\uf186'),  archive ('\uf187'),  bug ('\uf188'),  vk ('\uf189'),  weibo ('\uf18a'),  renren ('\uf18b'),
        pagelines ('\uf18c'),  stack_exchange ('\uf18d'),  arrow_circle_o_right ('\uf18e'),
        arrow_circle_o_left ('\uf190'),  caret_square_o_left ('\uf191'),  dot_circle_o ('\uf192'),  wheelchair ('\uf193'),
        vimeo_square ('\uf194'),  _try ('\uf195'),  plus_square_o ('\uf196'),

        ;

        public final char charCode;

        Glyph(char code) {
            this.charCode = code;
        }
    }

    private static final Font awesome = createFontAwesome();

    private static Font createFontAwesome() {
        try {
            InputStream stream = FontAwesomeIcon.class.getResourceAsStream("/fontawesome-webfont.ttf");
            return Font.createFont(Font.TRUETYPE_FONT, stream);
        } catch (FontFormatException | IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    FontAwesomeIcon(Glyph glyph, int size) {
        this(glyph.charCode, size);
    }

    FontAwesomeIcon(char iconID, int size) {
        this.iconID = iconID;
        this.size = size;
        font = awesome.deriveFont(Font.PLAIN, size);
    }

    @Override
    public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
        g.drawImage(createBufferedImage(), x, y, null);
    }

    private Image createBufferedImage() {
        BufferedImage buffer = new BufferedImage(getIconWidth(), getIconHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics = (Graphics2D) buffer.getGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setFont(font);
        graphics.setColor(Color.BLACK);

        int stringY = getIconHeight() - (getIconHeight()/4) + 1;
        graphics.drawString(String.valueOf(iconID), 0, stringY);

        graphics.dispose();
        return buffer;
    }

    @Override
    public int getIconHeight() {
        return size;
    }

    @Override
    public int getIconWidth() {
        return size;
    }
}
