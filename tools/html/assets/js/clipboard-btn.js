// Tooltip
$('.btn-copy').tooltip({
  trigger: 'hover',
  placement: 'bottom' 
});

function setTooltip(message) {
  button = $(event.target)
  oldMsg = button.tooltip().attr('data-original-title')
  button.tooltip()
    .attr('data-original-title', message)
    .tooltip('show');
  setTimeout(function() {
    button.tooltip()
    .attr('data-original-title', oldMsg)
    .tooltip('hide');
  }, 1000);
}

// Clipboard

var clipboard = new ClipboardJS('.btn-copy');

clipboard.on('success', function(e) {
  setTooltip('Copied!');
});

clipboard.on('error', function(e) {
  setTooltip('Failed :( - copy manually');
});
