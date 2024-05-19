export const adminProfile = async () => {
    return `<div class="screen-container">
    <!-- Logo on top -->
    <img id="logo-top" src="../../PNG-JPG/EhB%20logo%20long.jpeg" alt="Logo">

    <!-- Red line -->
    <div class="red-line"></div>

    <!-- Picture of the user -->
    <img id="user-picture" src="../../PNG-JPG/logo%20head.png" alt="User Picture">

    <!-- Hyperlinks -->
    <div class="hyperlinks">
        <a href="#"><img src="../../PNG-JPG/black%20dot.jpg" alt="Dot"> Profile Admin</a>
        <a href="#"><img src="../../PNG-JPG/black%20dot.jpg" alt="Dot"> Ban Library</a>
        <a href="#"><img src="../../PNG-JPG/black%20dot.jpg" alt="Dot"> Users Library</a>
    </div>

    <!-- Medialab button -->
    <button id="medialab-button">Medialab</button>
</div>`;
};